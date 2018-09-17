package com.onchange.www.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ChannelInItializerImpl extends ChannelInitializer<SocketChannel> {

	/***
	 * 该方法 目的是初始化channel 并将 channelHandler添加到pipeline容器中
	 */
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		final TestServerHandler t = new TestServerHandler();
		ch.pipeline().addLast(t); //将 Handler添加到 pipeline容器中
	}

}
