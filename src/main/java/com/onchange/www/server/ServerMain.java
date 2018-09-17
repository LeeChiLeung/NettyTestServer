package com.onchange.www.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerMain {
	
	private final Integer port = 8841;
	
	
	/**
	 * 该类用于引导 Netty 服务器
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		new ServerMain().start();
	}

	
	private void start() throws Exception {
		//创建 EventLoop
		EventLoopGroup group = new NioEventLoopGroup();
		System.out.println("服务端启动.");
		try {
			//绑定本地端口
			ServerBootstrap se = new ServerBootstrap();
			se.group(group);
			se.channel(NioServerSocketChannel.class);
			se.localAddress(new InetSocketAddress(port));
			//设置handler 处理 I/ O事件 
			se.childHandler(new ChannelInItializerImpl());
			//异步绑定服务器 
			ChannelFuture f = se.bind().sync();
			f.channel().closeFuture().sync();
			
			
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
