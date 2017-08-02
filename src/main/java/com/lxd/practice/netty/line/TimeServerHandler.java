package com.lxd.practice.netty.line;

import com.lxd.practice.util.DateTimeUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务回调
 * Created by liaoxudong on 2017/7/28.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);
    private final static String ORDER = "QUERY TIME"+System.getProperty("line.separator");
    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("服务启动完成...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            logger.error("待处理数据为空，忽略...");
            return;
        }
//        ByteBuf buf = (ByteBuf) msg;
        String req = (String)msg;
        /*byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);*/
//        String req = new String(bytes, "UTF-8");
        logger.info("* Recv Client msg："+req+";counter is:"+(++counter));

        String resp = (req.equals(ORDER.trim())? DateTimeUtils.formatDatetime1(System.currentTimeMillis()):"BAD ORDER")+System.getProperty("line.separator");
        logger.info("Send msg to Client："+resp);
        ByteBuf byteBuf = Unpooled.copiedBuffer(resp.getBytes());
        ctx.write(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("服务异常...",cause);
        ctx.close();
    }
}
