package cn.buu.note.netty.websocket;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * websocket 启动类
 * @author ABC
 *
 */
@Component
public class wsServer {
	//单例模式
	private static class SingletionWSServer{
		static final wsServer instance = new wsServer();
	}
	public static wsServer getInstance() {
		return SingletionWSServer.instance;
	}
	private EventLoopGroup main;
	private EventLoopGroup sub;
	private ServerBootstrap server;
	private ChannelFuture cf;
	
	private wsServer() {
		 main = new NioEventLoopGroup();
		 sub = new NioEventLoopGroup();
		 server = new ServerBootstrap();
		 server.group(main, sub)
			  .channel(NioServerSocketChannel.class)
			  .childHandler(new wsServerInitializer());
	}
	/**
	 * 开启netty 服务器
	 */
	public void start() {
		cf = server.bind(8088);
		System.err.println("netty websocket start...");
	}
}
