package cn.buu.note.webSocket;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.bind.annotation.CrossOrigin;

@ServerEndpoint("/chat")
public class WebSocket {
	private Session session;
	/**建立连接*/
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		System.out.println("已成功建立连接");
	}
	@OnMessage
	public void onMessage(String msg) {
		System.out.println("msg:"+msg);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
