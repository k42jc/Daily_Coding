package com.lxd.practice.netty.custom;

import com.lxd.practice.netty.custom.codec.NettyMessageDecoder;
import com.lxd.practice.netty.custom.codec.NettyMessageEncoder;
import com.lxd.practice.netty.custom.handler.HeartBeatRespHandler;
import com.lxd.practice.netty.custom.handler.ShakeHandAuthRespHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义协议Netty服务端
 * Created by liaoxudong on 2017/8/1.
 */
public class NettyServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    public static void bootstrap(String host,int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG,100)
        .handler(new LoggingHandler(LogLevel.DEBUG))
        .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                // 自定义消息解码器
                pipeline.addLast("NettyMessageDecoder",new NettyMessageDecoder(1024, 4,4));
                // 自定义消息编码器
                pipeline.addLast("NettyMessageEncoder", new NettyMessageEncoder());
                // 超时处理
                pipeline.addLast("ReadTimeOutHandler",new ReadTimeoutHandler(50));
                // 握手响应处理
                pipeline.addLast("ShakeHandRespHandler", new ShakeHandAuthRespHandler());
                // 心跳响应处理
                pipeline.addLast("HeartBeatRespHandler", new HeartBeatRespHandler());
            }
        });

        try {
            ChannelFuture sync = bootstrap.bind(/*host, */port).sync();
            logger.info("Netty服务器启动成功：{}:{}",host,port);
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyServer.bootstrap("127.0.0.1",8888);
    }
}
