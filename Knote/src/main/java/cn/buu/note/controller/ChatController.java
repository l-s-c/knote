package cn.buu.note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.common.JsonResult;
import cn.buu.note.entity.FriendDao;
import cn.buu.note.service.chat.ChatService;

@Controller
@RequestMapping("/chat")
@CrossOrigin
public class ChatController {
	@Resource
	private ChatService chatService;
	
	@RequestMapping("/sendMsg")
	@ResponseBody
	public JsonResult sendMsg(Integer frPhone,String msg) {
		return new JsonResult();
	}
	
	
	/**
	 * 加载好友
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadFrind") 
	@ResponseBody
	public JsonResult loadFrind(Integer phone) throws Exception {
		System.out.println("loadFrind");
		List<FriendDao> list = chatService.loadShowFrind(phone);
		return new JsonResult(list);
	}
}
