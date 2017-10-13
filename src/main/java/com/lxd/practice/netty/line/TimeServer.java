package com.lxd.practice.netty.line;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty 服务器
 * Created by liaoxudong on 2017/7/28.
 */
public class TimeServer {

    private static final Logger logger = LoggerFactory.getLogger(TimeServer.class);

    public static void bootstrap(int port) {
        // boss线程用于处理客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // work线程用于从SocketChannel中读写数据
        EventLoopGroup workGroup = new NioEventLoopGroup();

        // Netty Tcp服务启动帮助类
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 将boss、work两个线程组注册到启动器，设置TCP参数[so_backlog:1024]，再绑定I/O事件的处理类的具体实现(就是Reactor模式中的具体回调处理)
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new TimeServerHandler());
                    }
                });

        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        bootstrap(8888);
    }
}
