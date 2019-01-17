package cn.buu.note.service.chat;

import java.util.List;

import cn.buu.note.entity.FriendDao;

public interface ChatService {

	List<FriendDao> loadAllFrind() throws Exception;

}
