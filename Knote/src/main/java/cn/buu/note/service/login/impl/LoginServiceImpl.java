package cn.buu.note.service.login.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.buu.note.dao.UserDaoMapper;
import cn.buu.note.entity.UserDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.login.LoginService;
import cn.buu.note.utils.Md5Utils;

@Service
public class LoginServiceImpl implements LoginService{
	private static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	@Resource
	private UserDaoMapper userDaoMapper;
	@Override
	public boolean checkUser(String phone, String pwd) throws Exception {
		UserDao userDao ;
		try {
			userDao = userDaoMapper.selectByPrimaryKey(Long.valueOf(phone));
		}catch(Exception e) {
			logger.error(e);
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		if(userDao.getPwd().equals(Md5Utils.getMd5(pwd))) {
			return true;
		}
		return false;
	}
	@Override
	public UserDao selUserByOpenId(String openId) throws Exception {
		UserDao userDao ;
		try {
			userDao = userDaoMapper.selectByOpenId(openId);
		}catch(Exception e) {
			logger.error(e);
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return userDao;
	}
	@Override
	public void activateEmail(String phone) throws Exception {
		//已经激活
		if(true) {
			throw new CustomException(ErrorEnum.RE_ACTIVATE_ERROR);
		}
	}

}
