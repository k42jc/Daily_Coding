package com.lxd.practice.concurrent.netty.line;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Netty时间服务器客户端
 * <br>
 * Created by liaoxudong on 2017/7/28.
 */
public class TimeClient {
    private static TimeClientHandler handler;

    public TimeClient(TimeClientHandler handler) {
        TimeClient.handler = handler;
    }

    public void  connect(String host, int port) {
        // 客户端只需要一个线程组
        EventLoopGroup group = new NioEventLoopGroup();

        // 客户端启动辅助类
        Bootstrap bootstrap = new Bootstrap();
        // 线程组注册到启动器 设置tcp参数 并绑定回调处理器
        bootstrap.group(group)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.TCP_NODELAY,true)
        .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                socketChannel.pipeline().addLast(new StringDecoder());
                socketChannel.pipeline().addLast(handler);
            }
        });

        try {
            // 连接到服务器并同步等待
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 阻塞关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
