package com.lxd.practice.netty.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 使用分隔符解码器(DelimiterBasedFrameDecoder)的客户端演示
 * Created by liaoxudong on 2017/7/28.
 */
public class EchoClient {

    /**
     * 通过传入连接信息与同步器，将异步TCP客户端实现为同步获取数据
     * @param msg 连接信息
     * @param latch 同步器，同时也用于绑定返回数据
     */
    public static void connect(ClientMsg msg, ClientCountDownLatch latch) {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(EchoServer.DELIMITER.getBytes())));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new EchoClientHandler(msg, latch));
                    }
                });

        try {
            ChannelFuture future = bootstrap.connect(msg.getHost(), msg.getPort()).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }


}
