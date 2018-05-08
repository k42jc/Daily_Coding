package com.lxd.daily.netty.line;

import com.lxd.daily.util.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * 时间服务器客户端具体回调处理逻辑
 * <br>
 * Created by liaoxudong on 2017/7/28.
 */
public class TimeClientHandler extends ChannelHandlerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    // 待发送消息
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (StringUtils.isEmpty(msg)) {
            return;
        }
        logger.info("发送消息...");
        ByteBuf byteBuf = Unpooled.copiedBuffer(msg.getBytes());
        ctx.writeAndFlush(byteBuf);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            logger.error("获取信息为空，忽略...");
            return;
        }
        /*ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String readMsg = new String(bytes, "UTF-8");*/
        String readMsg = (String)msg;
        logger.info("* Recv Server resp："+readMsg);
        while (true) {
            // 模拟TCP粘包/拆包的情况
            System.out.println("请输入发送信息：");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine()+System.getProperty("line.separator");
            for(int i=0;i<100;i++){
                ByteBuf byteBuf1 = Unpooled.copiedBuffer(s.getBytes());
                logger.info("发送信息："+s);
                ChannelFuture future = ctx.writeAndFlush(byteBuf1);

            }

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("客户端异常退出...");
        ctx.close();
    }



}
