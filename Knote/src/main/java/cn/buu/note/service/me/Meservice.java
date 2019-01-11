package cn.buu.note.service.me;


import cn.buu.note.entity.UserDao;


public interface Meservice {

	UserDao loadMsg(Integer phone) throws Exception;
	
}
