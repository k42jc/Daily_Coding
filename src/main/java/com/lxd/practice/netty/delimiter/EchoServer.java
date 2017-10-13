package com.lxd.practice.netty.delimiter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>演示使用分隔符解码器(DelimiterBasedFrameDecoder)解决粘包拆包问题</b>
 * Created by liaoxudong on 2017/7/28.
 */
public class EchoServer {

    private final static Logger logger = LoggerFactory.getLogger(EchoServer.class);
    public static final String DELIMITER = "$_";

    public static void bootstrap(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 使用固定分隔符：$_ 作为消息读取完成标识
                        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(DELIMITER.getBytes())));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new EchoServerHandler());
                    }
                });

        try {
            ChannelFuture future = server.bind(port).sync();
            logger.info("Echo 服务器启动完成，port【{}】",port);
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
