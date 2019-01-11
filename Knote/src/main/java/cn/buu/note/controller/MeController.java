package cn.buu.note.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.BaseController;
import cn.buu.note.common.JsonResult;
import cn.buu.note.entity.UserDao;
import cn.buu.note.service.me.Meservice;

@Controller
@RequestMapping("/me")
@CrossOrigin
public class MeController extends BaseController{
		@Autowired
		private Meservice meService;
		@Autowired
		private HttpSession session;
		@RequestMapping("/loadMsg")
		@ResponseBody
		public JsonResult loadMsg() throws Exception {
			Integer phone = 123;//Integer.parseInt(session.getAttribute("phone").toString());
			UserDao userDao = meService.loadMsg(phone);
			return new JsonResult(userDao);
		}
}
