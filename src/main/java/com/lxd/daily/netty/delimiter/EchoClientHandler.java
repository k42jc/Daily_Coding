package com.lxd.daily.netty.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Echo客户端逻辑处理
 * Created by liaoxudong on 2017/7/28.
 */
public class EchoClientHandler extends ChannelHandlerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);

    private ClientMsg msg;
    private ClientCountDownLatch latch;

    public EchoClientHandler(ClientMsg msg, ClientCountDownLatch latch) {
        this.msg = msg;
        this.latch = latch;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String sendMsg = msg.getSendMsg().toString();
        logger.info("* Echo client send msg：{}",sendMsg);
        ByteBuf byteBuf = Unpooled.copiedBuffer((sendMsg + EchoServer.DELIMITER).getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String recvMsg = (String)msg;
        latch.setData(recvMsg);
        ctx.close();
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Echo 客户端异常");
    }
}
