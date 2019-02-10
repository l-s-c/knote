package cn.buu.note.netty.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.buu.note.enums.MsgActionEnum;
import cn.buu.note.service.chat.ChatService;
import cn.buu.note.utils.JsonUtils;
import cn.buu.note.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
/**
 * 处理消息的handler
 * TextWebSocketFrame : 在netty 中，是用于websocket专门处理文本的对象，frame 是消息的载体
 * @author ABC
 *
 */
public class wsHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private static ChannelGroup users 
				   = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	private static Map<String,Channel> manager = new HashMap<String,Channel>();
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		Channel currentChannel = ctx.channel();
		
		//1.获取消息
		//获取客户端传输过来的消息
				String content = msg.text();
				System.out.println("接收到的数据："+content);
				DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
				System.out.println("datacontent:"+dataContent);
				Integer action = dataContent.getAction();
				System.out.println("action:"+action);
		//2.判断消息类型，根据不同类型处理不同业务
				if(action == MsgActionEnum.CONNECT.type) {
					 //2.1 当websocket 第一次open时，初始化channel 关联phone 与 channel 
					String senderId =  dataContent.getChatMsg().getSenderId();
					manager.put(senderId, currentChannel);
					System.out.println("manager:"+manager);
				}else if(action == MsgActionEnum.CHAT.type) {
					//2.2 聊天类型的消息，保存聊天记录，标记消息签收状态,转发
					ChatMsg chatMsg = dataContent.getChatMsg();
					String msgText = chatMsg.getMsg();
					String receiverId = chatMsg.getReceiverId();
					String senderId = chatMsg.getSenderId();
					//保存到DB  未读状态
					//ChatService chatService = (ChatService) SpringUtil.getBean("chatServiceImpl");
					//String msgId =  chatService.sva();
					chatMsg.setMsgId("12346");;
					//转发
					System.out.println("manager2.0:"+manager);
					Channel receviceChannel = manager.get(receiverId);
					if(receviceChannel==null) {
						//离线状态  TODO   推送消息
					}else {
						//判断receviceChannel  是否在channelGroup中
						Channel findChannel = users.find(receviceChannel.id());
						if(findChannel!=null) {
							//用户在线
							receviceChannel.writeAndFlush(new TextWebSocketFrame(
									JsonUtils.objectToJson(chatMsg)));	
						}else {
							//用户离线   TODO 推送
						}
						
					}
				}else if(action == MsgActionEnum.PULL_FRIEND.type) {
					 //2.3 签收消息，针对具体消息进行签收
					// ChatService chatService = (ChatService) SpringUtil.getBean("chatServiceImpl");
					String msgIdStr  = dataContent.getExtand();			//
					String [] msgIds = msgIdStr.split(",");
					List<String> msgIdList = new ArrayList<>();
					for(String msgId : msgIds) {
						if(StringUtils.isNoneBlank(msgId)) {
							msgIdList.add(msgId);
						}
					}
					System.out.println("msgIdList:"+msgIdList);
					if(msgIdList!=null && msgIdList.isEmpty()  && msgIdList.size()>0) {
						//签收
					}
				}else if(action == MsgActionEnum.KEEPALIVE.type) {
					// 2.4 心跳类型
				}

	/*	for(Channel channel : users) {
			channel.writeAndFlush
			(new TextWebSocketFrame
					("服务器在"+LocalDateTime.now()+"收到消息："+content));
		}*/
		
/*		clients.writeAndFlush(new TextWebSocketFrame
					("服务器在"+LocalDateTime.now()+"收到消息："+content));*/
		
	}
	/**
	 * 客户端链接之后触发
	 * 获取客户端的channel 并且放到ChannelGroup中去进行管理
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().id().asLongText()+"已连接");
		users.add(ctx.channel());
	}
	/**
	 * 客户端链接断开之后触发
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//当触发此方法时，channelGroup 会自动移除对应的客户端的channel
		users.remove(ctx.channel());
		System.out.println("客户端断开");
		System.out.println("长Id:"+ctx.channel().id().asLongText());
		System.out.println("短Id:"+ctx.channel().id().asShortText());
		
		
	}
	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		//发生异常之后关闭channel,随后从channelGroup 中移除 
		ctx.channel().close();
		users.remove(ctx.channel());
	}

	
}
