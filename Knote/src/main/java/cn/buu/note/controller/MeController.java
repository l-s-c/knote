package cn.buu.note.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.dao.RedisOperator;
import cn.buu.note.entity.UserDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.login.LoginService;
import cn.buu.note.service.me.Meservice;
import cn.buu.note.utils.push.PushtoSingle;

@Controller
@RequestMapping("/me")
@CrossOrigin(allowCredentials="true",allowedHeaders="*")  
public class MeController extends BaseController{
		@Autowired
		private Meservice meService;
		@Autowired
		private LoginService loginservice;
		@Resource
		private RedisOperator redisOperator;
		@Resource
		private PushtoSingle pushtoSingle;
		/**
		 * 添加好友，直接添加双方为好友，提醒被动的一方
		 * @param session
		 * @param ophone
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/addFriend")
		@ResponseBody
		public JsonResult addFriend(HttpSession session,Integer ophone) throws Exception {
			Integer phone;
			try {
				phone = Integer.parseInt(redisOperator.get(session.getId()));
			}catch(Exception e) {
				e.printStackTrace();
				throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
			}
			/**添加到好友表*/
			meService.addFriend(phone,ophone);
			meService.addFriend(ophone,phone);
			/**添加成功，提醒被动方*/
			String cid = loginservice.getCidPhone(phone);
			System.out.println("cid:"+cid);
			try {
				//pushtoSingle.push(cid,"添加好友",ophone+"已成为您的好友");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return new JsonResult();
		}
		
		
		/**
		 * 判断电话是否为当前登录人的 好友
		 * @param session
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/checkIfFriend")
		@ResponseBody
		public JsonResult checkIfFriend(HttpSession session,Integer ophone) throws Exception {
			Integer phone;
			try {
				phone = Integer.parseInt(redisOperator.get(session.getId()));
			}catch(Exception e) {
				e.printStackTrace();
				throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
			}
			boolean b = meService.checkIfFriend(phone,ophone);
			System.out.println("b:"+b);
			return new JsonResult(b);
		}
		
		/**
		 * 通过phone获取信息
		 * @param phone
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/getOtherMsg")
		@ResponseBody
		public JsonResult getOtherMsg(Integer phone) throws Exception {
			UserDao userDao = meService.loadMsg(phone);
			return new JsonResult(userDao);
		}	
		/**
		 * 获取本人账号信息
		 * @param session
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/loadMsg")
		@ResponseBody
		public JsonResult loadMsg(HttpSession session) throws Exception {
			Integer phone;
			try {
				phone = Integer.parseInt(redisOperator.get(session.getId()));
			}catch(Exception e) {
				e.printStackTrace();
				throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
			}
			UserDao userDao = meService.loadMsg(phone);
			return new JsonResult(userDao);
		}
}
