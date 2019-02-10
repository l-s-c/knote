package cn.buu.note.netty.websocket;

import java.io.Serializable;

public class ChatMsg implements Serializable {

	
	
	private static final long serialVersionUID = -3132263652872143985L;
	private String senderId;		// 发送者的用户id	
	private String receiverId;		// 接受者的用户id
	private String msg;				// 聊天内容
	private String msgId;			// 用于消息的签收
	
	@Override
	public String toString() {
		return "ChatMsg [senderId=" + senderId + ", receiverId=" + receiverId + ", msg=" + msg + ", msgId=" + msgId
				+ "]";
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}
