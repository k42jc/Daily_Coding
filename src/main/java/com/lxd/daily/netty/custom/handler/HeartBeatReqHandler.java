package com.lxd.daily.netty.custom.handler;

import com.lxd.daily.netty.custom.pojo.Header;
import com.lxd.daily.netty.custom.pojo.MessageType;
import com.lxd.daily.netty.custom.pojo.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 自定义协议心跳请求
 * 当前节点在调用链的位置在握手之后
 * Created by liaoxudong on 2017/8/1.
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(HeartBeatReqHandler.class);
    private volatile ScheduledFuture<?> heartBeatFuture;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 心跳请求在握手请求之后
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.SHAKE_HAND_RESP.getCode()) {
            logger.info("客户端完成握手，创建任务，开始心跳检测...");
            // 创建定时任务开始心跳 立即执行 20一次
            heartBeatFuture = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 0, 20, TimeUnit.SECONDS);
        } else if(message.getHeader() != null && message.getHeader().getType() == MessageType.HEART_BEAT_RESP.getCode()){
            // 心跳响应处理
            logger.info("收到服务端心跳响应：{}\r",message);
        } else{
            // 否则透传
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeatFuture != null) {
            heartBeatFuture.cancel(true);
            heartBeatFuture = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 心跳任务
     */
    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;
        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage req = buildHeartBeat();
            logger.info("发送心跳检测：{}",req);
            ctx.writeAndFlush(req);
        }
    }

    /**
     * 创建心跳检测请求报文
     * @return
     */
    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEART_BEAT_REQ.getCode());
        message.setHeader(header);
        return message;
    }
}
