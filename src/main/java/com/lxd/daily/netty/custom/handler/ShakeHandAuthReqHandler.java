package com.lxd.daily.netty.custom.handler;

import com.lxd.daily.constants.Constants;
import com.lxd.daily.netty.custom.pojo.Body;
import com.lxd.daily.netty.custom.pojo.Header;
import com.lxd.daily.netty.custom.pojo.MessageType;
import com.lxd.daily.netty.custom.pojo.NettyMessage;
import com.lxd.daily.util.DateTimeUtils;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 客户端握手认证
 * Created by liaoxudong on 2017/8/1.
 */
public class ShakeHandAuthReqHandler extends ChannelHandlerAdapter{
    private final static Logger logger = LoggerFactory.getLogger(ShakeHandAuthReqHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyMessage message = buildLoginReq();
        logger.info("握手请求消息：{}",message);
        ctx.writeAndFlush(message);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (msg == null) {
            logger.error("握手失败，关闭连接...");
            ctx.close();
        }

        // 如果是握手应答 需要判断是否认证成功
        if(message != null && message.getHeader().getType() == MessageType.SHAKE_HAND_RESP.getCode()){
            String dateTime = message.getBody().getDateTime();
            logger.info("收到握手请求，客户端请求时间：{}，服务器当前时间：{}",dateTime, DateTimeUtils.formatDatetime1(new Date()));
            String requestMsg = message.getBody().getMessage();
            if (!Constants.SUCCESS.equals(requestMsg)) {
                logger.error("握手失败，关闭连接...");
                ctx.close();
            }else {
                logger.info("握手成功：{}",requestMsg);
                ctx.fireChannelRead(msg);
            }
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * 构建握手请求消息
     * @return
     */
    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();

        header.setType(MessageType.SHAKE_HAND_REQ.getCode());
        message.setHeader(header);
        Body body = new Body();
        body.setDateTime(DateTimeUtils.formatDatetime1(new Date()));
        Map<String, Object> extents = new LinkedHashMap<>();
        extents.put("hello","hahahah");
        body.setExtents(extents);
        message.setBody(body);

        return message;
    }
}
