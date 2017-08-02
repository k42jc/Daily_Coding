package com.lxd.practice.netty.webSocket;

import com.lxd.practice.util.DateTimeUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * WebSocket服务端请求处理
 * 使用Ctrl+I快速重写抽象/接口方法
 * Created by liaoxudong on 2017/7/31.
 */
public class WebSocketServerChannelHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerChannelHandler.class);
    private static final String WEBSOCKET_URL = "ws://localhost:8888/websocket";
    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        // HTTP请求处理
        if (msg instanceof FullHttpRequest) {
            handlerHttpRequest(ctx, (FullHttpRequest) msg);
        }

        // WebSocket请求处理
        if (msg instanceof WebSocketFrame) {
            handlerWebSocketRequest(ctx, (WebSocketFrame) msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 处理WebSocket请求
     *
     * @param ctx
     * @param msg WebSocket请求数据
     */
    private void handlerWebSocketRequest(ChannelHandlerContext ctx, WebSocketFrame frame) {
        logger.info("{} Recv an WebSocket request: {}",ctx.channel(),frame);
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            logger.info("Close WebSocket connection.");
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            logger.info("WebSocket ping request.");
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本示例程序仅支持字符串 不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported!",frame.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame)frame).text();
        logger.info("{} recv {}",new Object[]{ctx.channel(),request});
        TextWebSocketFrame response = new TextWebSocketFrame(request + "，欢迎使用Netty WebSocket服务，北京时间：" + DateTimeUtils.formatDatetime1(new Date()));
        ctx.channel().write(response);
        logger.info("Reponse :{}",response);
    }


    /**
     * 处理HTTP请求
     *
     * @param ctx
     * @param msg HTTP请求消息体
     */
    private void handlerHttpRequest(ChannelHandlerContext ctx, FullHttpRequest msg) {
        logger.info("Channel: {} Recv an HTTP request: {}",ctx.channel(),msg);
        // HTTP解码失败或请求类型不是WebSocket握手请求，拒绝
        if (!msg.decoderResult().isSuccess() || !"websocket".equals(msg.headers().get("Upgrade"))) {
            sendHttpResponse(ctx, msg, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        logger.info("WebSocket handshake request building...");

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(WEBSOCKET_URL, null, false);
        handshaker = handshakerFactory.newHandshaker(msg);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), msg);
            logger.info("Finish WebSocket handshake.");
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse response) {
        // 非正常应答
        if (response.status().code() != 200) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(byteBuf);
            byteBuf.release();

            setContentLength(response, response.content().readableBytes());
        }
        // 如果是非keep-alive，则关闭连接
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
        if (!isKeepAlive(req) || response.status().code() != 200) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }


    }

    /**
     * 设置响应消息体长度
     *
     * @param response
     * @param i        消息体长度
     */
    private void setContentLength(DefaultFullHttpResponse response, int i) {
        response.headers().setInt("Content-Length", i);
    }

    /**
     * 判断当前请求是否Keep-Alive
     *
     * @param request
     * @return
     */
    private boolean isKeepAlive(FullHttpRequest request) {
        if ("Keep-Alive".equals(request.headers().get("Connection"))) {
            return true;
        }
        return false;
    }


}
