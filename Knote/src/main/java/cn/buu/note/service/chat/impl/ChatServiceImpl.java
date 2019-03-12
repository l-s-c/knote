package cn.buu.note.service.chat.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.buu.note.dao.ChatLogMapper;
import cn.buu.note.dao.FriendDaoMapper;
import cn.buu.note.dao.RedisOperator;
import cn.buu.note.entity.ChatLog;
import cn.buu.note.entity.FriendDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.netty.websocket.ChatMsg;
import cn.buu.note.service.chat.ChatService;
import cn.buu.note.utils.id.IdUtils;
@Service
public class ChatServiceImpl implements ChatService{
	@Resource
	private FriendDaoMapper friendDaoMapper;
	@Resource 
	private ChatLogMapper chatLogMapper;
	@Resource
	private HttpSession session;
	@Resource
	private RedisOperator redisOperator;
	@Override
	public List<FriendDao> loadAllFrind() throws Exception {
		List<FriendDao> list = null;
		session.setAttribute("phone",123);
		Integer myPhone = Integer.parseInt(session.getAttribute("phone").toString());
		System.out.println("myphone:"+myPhone);
		try {
			list = friendDaoMapper.selectByMyPhone(myPhone);
			System.out.println("list:"+list);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return list;
	}
	@Override
	public List<FriendDao> loadShowFrind() throws Exception {
		List<FriendDao> list = null;
		Integer myPhone = null;
		try {
			myPhone = Integer.parseInt(redisOperator.get(session.getId()));
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.USER_LOSE_ERROR);
		}
		System.out.println("myphone:"+myPhone);
		try {
			list = friendDaoMapper.selectShowByMyPhone(myPhone);
			System.out.println("list:"+list);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return list;
	}
	/**
	 * 保存聊天记录
	 */
	@Override
	public String insertMsg(ChatMsg chatMsg) throws Exception {
		System.out.println("chatMsg:"+chatMsg);
		String chatId = IdUtils.getId();
		ChatLog chatlog = new ChatLog();

		chatlog.setId(Long.parseLong(chatId));
		chatlog.setSendPhone(Integer.parseInt(chatMsg.getSenderId()));
		chatlog.setToPhone(Integer.parseInt(chatMsg.getReceiverId()));
		chatlog.setText(chatMsg.getMsg());
		System.out.println("chatLog:"+chatlog);
		try {
			chatLogMapper.insertSelective(chatlog);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return chatId;
	}

}
