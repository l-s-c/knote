package cn.buu.note.service.chat.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.FriendDaoMapper;
import cn.buu.note.entity.FriendDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.chat.ChatService;
@Service
public class ChatServiceImpl implements ChatService{
	@Resource
	private FriendDaoMapper friendDaoMapper;
	@Resource
	private HttpSession session;
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
	public List<FriendDao> loadShowFrind(Integer myPhone) throws Exception {
		List<FriendDao> list = null;
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

}
