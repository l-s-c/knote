package cn.buu.note.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.dao.RedisOperator;
import cn.buu.note.entity.UserDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.login.LoginService;


@Controller
@RequestMapping("/login")
@CrossOrigin(allowCredentials="true",allowedHeaders="*")     //处理跨域请求
public class LoginController extends BaseController{
	private static Logger logger = Logger.getLogger(LoginController.class);
		@Resource
		private LoginService loginService;
		
		@Resource
		private RedisOperator redisOperator;

		Map data = new HashMap();
		@RequestMapping("/test")
		@ResponseBody
		public String test(String name) throws Exception {
			data.put("222","222");
			data.put(name, name);
			System.out.println(data);
			return "";
			
		}
		
		/**
		 * 获取当前用户对象
		 * @param session
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/setUserStorage")
		@ResponseBody
		public JsonResult setUserStorage(HttpServletRequest request) throws Exception {
			Integer phone ;
			UserDao user = null;
			try {
				String sessionId =  request.getSession().getId();
				String p = redisOperator.get(sessionId);
				 phone = Integer.parseInt(p);
				 user = loginService.getUserByPhone(phone);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("setUserStorage:"+user);
			return new JsonResult(user);
		}
		
		/**
		 * 获取当前登录用户电话号码
		 * @param session
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/getUserPhone")
		@ResponseBody
		public JsonResult getUserPhone(HttpSession session) throws Exception {
			Object phone = redisOperator.get(session.getId());
			return new JsonResult(phone);
		}
		
		/**
		 *
		 * @param phone
		 * @param session
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/{phone}/checkUser")
		@ResponseBody
		public JsonResult checkUser(@PathVariable Integer phone,HttpSession session) throws Exception {
			logger.info(phone);
			loginService.checkUser(phone);
			session.setAttribute("phone",phone);
			session.setMaxInactiveInterval(-1);
			return new JsonResult();
			
		}
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
		@Transactional					
		public JsonResult test(UserDao userDao,String email) throws Exception {
			logger.info(userDao);
			if(email==null&&email.isEmpty()) {						//邮箱正则
				throw new CustomException(ErrorEnum.ILL_PARAMETER_ERROR,"邮箱格式错误");
			}
			//是否注册过
			
			//保存用户
			loginService.saveUser(userDao);
			//发送邮箱激活码
			loginService.sendEmail(email,userDao);
			return new JsonResult();
		}
		/**
		 * 登录
		 * @param phone
		 * @param pwd
		 * @param openId.
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/login")
		@ResponseBody
		public JsonResult login(HttpServletRequest request ,UserDao user) throws Exception {
			
				String openId= user.getOpenId();
				String phone = user.getPhone().toString();
				String pwd = user.getPwd();
				logger.info("login:"+phone+" , "+pwd+" , "+openId);
				//if(openId.equals("0000")) {
					boolean b = loginService.checkUser(phone,pwd);
					if(b) {
						b = loginService.ifAclitave(phone);
						if(b) {
							//登陆成功
							//session.setAttribute("phone", phone);
							String sessionId = request.getSession().getId();
							redisOperator.set(sessionId, phone);
							//更新cid
							try {
								if(!openId.equals("")&&openId.length()>0) {
									loginService.updataCID(Integer.parseInt(phone),openId);
								}
							}catch(Exception e) {
								e.printStackTrace();
							}
							return new JsonResult();
						}else {
							throw new CustomException(ErrorEnum.ILL_PARAMETER_ERROR,"用户未激活");
						}
						
					}else {
						throw new CustomException(ErrorEnum.ILL_PARAMETER_ERROR,"用户名或密码错误");
					}
		}
		
		/**
		 * 自动登录记录session
		 * @param phone
		 * @param session
		 * @return
		 */
		@RequestMapping("/loginAutomatic")
		@ResponseBody
		public JsonResult loginAutomatic(Integer phone,HttpSession session) {
			redisOperator.set(session.getId(),phone.toString());
			return new JsonResult();
		}
}
