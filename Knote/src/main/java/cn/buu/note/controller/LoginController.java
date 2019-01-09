package cn.buu.note.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.entity.UserDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.login.LoginService;
import cn.buu.note.utils.MailUtils;


@Controller
@RequestMapping("/login")
@CrossOrigin       //处理跨域请求
public class LoginController extends BaseController{
	private static Logger logger = Logger.getLogger(LoginController.class);
		@Resource
		private LoginService loginService;
		
		/**
		 * 激活地址
		 * @param phone
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/activate")
		@ResponseBody
		public JsonResult activate(byte[] user) throws Exception {
			logger.info(user);
			loginService.activateEmail(user);
			return new JsonResult();
			
		}
		
		/**
		 * 注册
		 * @param userDao
		 * @param email
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/register")
		@ResponseBody
		public JsonResult test(UserDao userDao,String email) throws Exception {
			logger.info(userDao);
			if(email==null&&email.isEmpty()) {						//邮箱正则
				throw new CustomException(ErrorEnum.ILL_PARAMETER_ERROR,"邮箱格式错误");
			}
			//发送邮箱激活码
			loginService.sendEmail(email,userDao);
			return new JsonResult();
		}
		/**
		 * 登录
		 * @param phone
		 * @param pwd
		 * @param openId
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="/{phone}/{pwd}/{openId}/login",method= {RequestMethod.GET},consumes= {CONTENT_TYPE_FORMED})
		@ResponseBody
		public JsonResult login(@PathVariable String phone,@PathVariable String pwd,@PathVariable String openId) throws Exception {
				logger.info("login:"+phone+" , "+pwd+" , "+openId);
				if(openId.equals("0000")) {
					boolean b = loginService.checkUser(phone,pwd);
					if(b) {
						//登陆成功
						return new JsonResult();
					}else {
						throw new CustomException(ErrorEnum.ILL_PARAMETER_ERROR,"用户名或密码错误");
					}
				}else {
					UserDao userDao = loginService.selUserByOpenId(openId);
					if(userDao==null) {
						//第一次用微信登录
						return new JsonResult(2,"第一次微信登录");
					}else {
						//登陆成功
						return new JsonResult();
					}
				}
		}
}
