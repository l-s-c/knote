package cn.buu.note.service.chat;

import java.util.List;

import cn.buu.note.entity.FriendDao;
import cn.buu.note.netty.websocket.ChatMsg;

public interface ChatService {

	List<FriendDao> loadAllFrind() throws Exception;

	List<FriendDao> loadShowFrind() throws Exception;

	String insertMsg(ChatMsg chatMsg) throws Exception;

}
