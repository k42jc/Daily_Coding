package com.lxd.practice.netty.custom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.serial.SerialMarshallerFactory;

/**
 * 自定义协议MarshallingEncoder
 * Created by liaoxudong on 2017/8/1.
 */
public class CustomMarshallingEncoder extends MarshallingEncoder{
    public CustomMarshallingEncoder() {
        super(new DefaultMarshallerProvider(new SerialMarshallerFactory(), new MarshallingConfiguration()));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        super.encode(ctx, msg, out);
    }
}
