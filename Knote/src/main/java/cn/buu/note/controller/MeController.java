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
import cn.buu.note.service.me.Meservice;

@Controller
@RequestMapping("/me")
@CrossOrigin(allowCredentials="true",allowedHeaders="*")  
public class MeController extends BaseController{
		@Autowired
		private Meservice meService;
		@Resource
		private RedisOperator redisOperator;
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
