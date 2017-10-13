package com.lxd.practice.netty.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于WebSocket协议栈的Netty服务端
 * Created by liaoxudong on 2017/7/31.
 */
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    public static void bootStrap(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(/*Runtime.getRuntime().availableProcessors()*2*/);
        EventLoopGroup workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2+1);


        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
        .channel(NioServerSocketChannel.class)
//        .option(ChannelOption.SO_BACKLOG,1024)
        .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                // 添加针对WebSocket协议栈的前置解码器
                pipeline.addLast("http-codec", new HttpServerCodec());
                pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                pipeline.addLast("http-chunked",new ChunkedWriteHandler());
                pipeline.addLast("handler",new WebSocketServerChannelHandler());
            }
        });

        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            logger.info("WebSocket server boot success! Port：{}",port);
            logger.info("Please open your Browser and navigate to http://localhost:{}/",port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        bootStrap(8888);
    }
}
