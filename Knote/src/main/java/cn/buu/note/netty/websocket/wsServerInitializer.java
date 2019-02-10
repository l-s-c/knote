package cn.buu.note.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * websocket 初始化器
 * @author ABC
 *
 */
public class wsServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = 	ch.pipeline();
		//http编解码
		pipeline.addLast(new HttpServerCodec());
		//对大数据流的支持
		pipeline.addLast(new ChunkedWriteHandler());
		//对httpMessage 进行 聚合，整合成FullHttpRequest 或 FullHttpResponse
		// 几乎在netty 中的编程，都会用到此handler
		pipeline.addLast(new HttpObjectAggregator(1024*64));
		
		//================================  以上是用于支持http协议   ==============================================
		/**
		 * webSocket  服务器处理的协议，用于指定给客户端连接访问的路由：/ws
		 * 本handler 会帮你处理一些繁重的复杂的事
		 * 会帮你处理握手动作，handshaking(close,ping ,pong)
		 * 对于webSocket 来讲  都是以frames 进行传输的，不同的数据类型对应的frames 也不同
		 */
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		//自定义的handler
		pipeline.addLast(new wsHandler());
	}

}
