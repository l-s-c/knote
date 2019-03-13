package cn.buu.note.service.me;


import cn.buu.note.entity.UserDao;


public interface Meservice {

	UserDao loadMsg(Integer phone) throws Exception;

	boolean checkIfFriend(Integer phone, Integer ophone) throws Exception;

	int addFriend(Integer phone, Integer ophone) throws Exception;
	
}
