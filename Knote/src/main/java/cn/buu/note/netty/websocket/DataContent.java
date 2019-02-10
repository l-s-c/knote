package cn.buu.note.netty.websocket;

import java.io.Serializable;

public class DataContent implements Serializable {


	private static final long serialVersionUID = 2703915031328592155L;
	private Integer action;		// 动作类型
	private ChatMsg chatMsg;	// 用户的聊天内容entity
	private String extand;		// 扩展字段
	
	@Override
	public String toString() {
		return "DataContent [action=" + action + ", chatMsg=" + chatMsg + ", extand=" + extand + "]";
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public ChatMsg getChatMsg() {
		return chatMsg;
	}
	public void setChatMsg(ChatMsg chatMsg) {
		this.chatMsg = chatMsg;
	}
	public String getExtand() {
		return extand;
	}
	public void setExtand(String extand) {
		this.extand = extand;
	}
}
