package com.lxd.daily.netty.custom;

import com.lxd.daily.constants.Constants;
import com.lxd.daily.netty.custom.codec.NettyMessageDecoder;
import com.lxd.daily.netty.custom.codec.NettyMessageEncoder;
import com.lxd.daily.netty.custom.handler.HeartBeatReqHandler;
import com.lxd.daily.netty.custom.handler.ShakeHandAuthReqHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 自定义协议Netty客户端
 * Created by liaoxudong on 2017/8/1.
 */
public class NettyClient {

    // 创建定时执行器 线程池中维持一个线程
    private ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

    EventLoopGroup group = new NioEventLoopGroup();

    private void connect(String host, int port) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
            .channel(NioSocketChannel.class)
        .option(ChannelOption.TCP_NODELAY,true)
        .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                // 自定义协议前置解码器
                pipeline.addLast("NettyMessageDecoder",new NettyMessageDecoder(1024, 4,4));
                // 自定义协议前置编码
                pipeline.addLast("NettyMessageEncoder",new NettyMessageEncoder());
                // 超时处理 50s重连
                pipeline.addLast("ReadTimeoutHandler", new ReadTimeoutHandler(50));
                // 握手认证请求处理
                pipeline.addLast("ShakeHandAuthHandler", new ShakeHandAuthReqHandler());
                // 心跳检测请求处理
                pipeline.addLast("HeartBeatHandler", new HeartBeatReqHandler());
            }
        });

        try {
            // 分别设置远程连接与本地连接
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host,port)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 断线重连机制
            schedule.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        // 发起重连
                        connect(Constants.LOCALHOST, 8888);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1",8888);
    }

}
