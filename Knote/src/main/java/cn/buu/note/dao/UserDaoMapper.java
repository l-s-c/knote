package cn.buu.note.dao;

import cn.buu.note.entity.UserDao;

public interface UserDaoMapper {
    int deleteByPrimaryKey(Long id);

	int insert(UserDao record);

	int insertSelective(UserDao record);

	UserDao selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserDao record);

	int updateByPrimaryKey(UserDao record);

	UserDao selectByOpenId(String openId);

	UserDao selectUserByPhone(Integer phone);

	boolean ifAclitvate(Integer parseInt);

	/*UserDao getUserByPhone(Integer phone);*/
}