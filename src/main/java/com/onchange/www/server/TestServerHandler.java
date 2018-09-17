package com.onchange.www.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@Sharable
public class TestServerHandler extends ChannelInboundHandlerAdapter {

	/***
	 * 该类是属于处理客户端消息的Handler 运行于channel pipline容器中
	 */
	
	//该方法用于处理客户端消息
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       ByteBuf buf = (ByteBuf) msg;
       System.out.println("收到客户端消息"+buf.toString(CharsetUtil.UTF_8));
       ctx.write(buf);//将消息推到下一个 handler
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
		//将消息推送到客户端
	}
	//捕获异常 并关闭该channel
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
	
	
}
