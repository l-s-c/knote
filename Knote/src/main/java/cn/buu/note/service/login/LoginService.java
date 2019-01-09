package cn.buu.note.service.login;

import cn.buu.note.entity.UserDao;

public interface LoginService {
	/**
	 * 登录检验 手机号与密码匹配
	 * @param phone
	 * @param pwd
	 * @return
	 * @throws Exception 
	 */
	boolean checkUser(String phone, String pwd) throws Exception;

	UserDao selUserByOpenId(String openId) throws Exception;

	void activateEmail(String phone) throws Exception;

}
