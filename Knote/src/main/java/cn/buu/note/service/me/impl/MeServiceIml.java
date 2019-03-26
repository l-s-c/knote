package cn.buu.note.service.me.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.FriendDaoMapper;
import cn.buu.note.dao.UserDaoMapper;
import cn.buu.note.entity.FriendDao;
import cn.buu.note.entity.UserDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.me.Meservice;
@Service
public class MeServiceIml implements Meservice{
	@Resource
	private UserDaoMapper userDaoMapper;
	@Resource
	private FriendDaoMapper friendDaoMapper;
	@Override
	public UserDao loadMsg(Integer phone) throws Exception {
		UserDao user ;
		try {
			user = userDaoMapper.selectUserByPhone(phone);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return user;
	}
	@Override
	public boolean checkIfFriend(Integer phone, Integer ophone) throws Exception {
		if(phone==ophone) {
			return true;
		}
		boolean b = false;
		try {
			b = friendDaoMapper.checkIfFriend(phone,ophone);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return b;
	}
	@Override
	public int addFriend(Integer phone, Integer ophone) throws Exception {
		FriendDao friendDao = new FriendDao();
		friendDao.setMyPhone(Long.parseLong(phone.toString()));
		friendDao.setFrPhone(Long.parseLong(ophone.toString()));
		int rows = 0;
		try {
			friendDaoMapper.insertSelective(friendDao);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return rows;
	}

}
