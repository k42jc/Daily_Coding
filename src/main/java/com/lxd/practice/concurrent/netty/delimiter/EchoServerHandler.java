package com.lxd.practice.concurrent.netty.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liaoxudong on 2017/7/28.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(EchoServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            logger.error("接收到的信息为空...");
            return;
        }
        String readMsg = (String)msg;
        logger.info("* Echo server recv msg：{}",readMsg);

        String sendMsg = readMsg + EchoServer.DELIMITER;
        logger.info("# Echo server send msg：{}",sendMsg);
        ByteBuf byteBuf = Unpooled.copiedBuffer(sendMsg.getBytes());
        ctx.write(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
