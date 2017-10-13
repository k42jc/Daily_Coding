package com.lxd.practice.netty.custom.handler;

import com.lxd.practice.netty.custom.pojo.Header;
import com.lxd.practice.netty.custom.pojo.MessageType;
import com.lxd.practice.netty.custom.pojo.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 心跳检测响应处理
 * Created by liaoxudong on 2017/8/1.
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HeartBeatRespHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 处理心跳检测请求消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEART_BEAT_REQ.getCode()) {
            logger.info("收到客户端心跳检测：{}", message);
            NettyMessage resp = buildHeartBeat();
            logger.info("响应心跳：{}", resp);
            ctx.writeAndFlush(resp);
        } else {
            // 否则透传
            ctx.fireChannelRead(msg);
        }


    }

    /**
     * 创建心跳检测响应消息
     *
     * @return
     */
    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEART_BEAT_RESP.getCode());
        message.setHeader(header);
        return message;
    }
}
