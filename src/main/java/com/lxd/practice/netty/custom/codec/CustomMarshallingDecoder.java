package com.lxd.practice.netty.custom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.serial.SerialMarshallerFactory;

/**
 * 自定义协议MarshallingDecoder
 * Created by liaoxudong on 2017/8/1.
 */
public class CustomMarshallingDecoder extends MarshallingDecoder{
    public CustomMarshallingDecoder(int maxObjectSize) {
        super(new DefaultUnmarshallerProvider(new SerialMarshallerFactory(),new MarshallingConfiguration()),maxObjectSize);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return super.decode(ctx, in);
    }
}
