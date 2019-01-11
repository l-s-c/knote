package cn.buu.note.service.me.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.UserDaoMapper;
import cn.buu.note.entity.UserDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.me.Meservice;
@Service
public class MeServiceIml implements Meservice{
	@Resource
	private UserDaoMapper userDaoMapper;
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

}
