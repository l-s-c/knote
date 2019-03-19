package cn.buu.note.service.login.impl;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.UserDaoMapper;
import cn.buu.note.entity.UserDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.login.LoginService;
import cn.buu.note.utils.MailUtils;
import cn.buu.note.utils.Md5Utils;
import cn.buu.note.utils.SerializableUtils;

@Service
public class LoginServiceImpl implements LoginService{
	//private static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	@Resource
	private UserDaoMapper userDaoMapper;
	@Override
	public boolean checkUser(String phone, String pwd) throws Exception {
		UserDao userDao ;
		try {
			userDao = userDaoMapper.selectByPrimaryKey(Long.valueOf(phone));
		}catch(Exception e) {
			e.printStackTrace();
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
			//logger.error(e);
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return userDao;
	}
	@Override
	public void activateEmail(byte[] byt) throws Exception {
		//已经激活
	/*	if(true) {
			throw new CustomException(ErrorEnum.RE_ACTIVATE_ERROR);
		}*/
		UserDao userDao = (UserDao) SerializableUtils.unserializable(byt);
		userDao.setIsAclivate(1);
		userDaoMapper.updateByPrimaryKey(userDao);
	}
	@Override
	public void sendEmail(String email,UserDao userDao) throws Exception {
		String code = "Http://120.79.10.49:8888/Knote/login/activate?user="+SerializableUtils.serializable(userDao)+"";
		try {
			MailUtils.sendMail(email, code);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.EMAIL_ERROR);
		}
	}
	@Override
	public void saveUser(UserDao userDao) throws Exception {
		try {
			userDao.setPwd(Md5Utils.getMd5(userDao.getPwd()));
			userDaoMapper.insert(userDao);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.REGISTER_ERROR);
		}
		
	}
	@Override
	public void checkUser(Integer phone) throws Exception {
		if(phone==null) {
			throw new CustomException(ErrorEnum.USER_EXIT_ERROR);
		}
		UserDao userDao = userDaoMapper.selectUserByPhone(phone);
		if(userDao!=null) {
			throw new CustomException(ErrorEnum.USER_EXIT_ERROR);
		}
	}
	@Override
	public boolean ifAclitave(String phone) {
		boolean b = userDaoMapper.ifAclitvate(Integer.parseInt(phone));
		return b;
	}
	@Override
	public UserDao getUserByPhone(Integer phone) {
		UserDao user = new UserDao();
		user = userDaoMapper.selectUserByPhone(phone);
		return user;
	}
	@Override
	public void updataCID(Integer phone,String openId) {
		try {
			userDaoMapper.updateCID(phone,openId);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getCidPhone(Integer phone) throws Exception {
		String cid = null;
		try {
		    cid = userDaoMapper.getCidPhone(phone);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return cid;
	}

}
