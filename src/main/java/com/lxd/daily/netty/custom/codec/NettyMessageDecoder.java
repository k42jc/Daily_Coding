package com.lxd.daily.netty.custom.codec;

import com.lxd.daily.netty.custom.pojo.Body;
import com.lxd.daily.netty.custom.pojo.Header;
import com.lxd.daily.netty.custom.pojo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Netty自定义协议服务器消息解码器
 * Created by liaoxudong on 2017/8/1.
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    CustomMarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.marshallingDecoder = new CustomMarshallingDecoder(maxFrameLength);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 避免半包读写
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        in = frame;
        NettyMessage message = new NettyMessage();
        Header header = new Header();

        // 按编码时的写入顺序读
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setPriority(in.readByte());
        header.setType(in.readByte());
        header.setSessionID(in.readLong());

        // 读取头部扩展域
//        int headerExtentsSize = in.readInt();
        int keyLength = in.readInt();
        byte[] keyBytes = null;
        String key = null;
        if (keyLength > 0) {
            // 读取消息体
            /*Body body = new Body();
            keyBytes = new byte[keyLength];
            in.readBytes(keyBytes);
            body.setMessage(new String(keyBytes, "UTF-8"));

            keyLength = in.readInt();
            keyBytes = new byte[keyLength];
            in.readBytes(keyBytes);
            body.setDateTime(new String(keyBytes, "UTF-8"));

            int bodyExtentsSize = in.readInt();
            if (bodyExtentsSize > 0) {
                Map<String, Object> bodyExtents = new LinkedHashMap<>(bodyExtentsSize);
                for (int i = 0; i < bodyExtentsSize; i++) {
                    keyLength = in.readInt();
                    keyBytes = new byte[keyLength];
                    in.readBytes(keyBytes);
                    key = new String(keyBytes, "UTF-8");
                    bodyExtents.put(key, marshallingDecoder.decode(ctx, in));
                }
                body.setExtents(bodyExtents);
            }*/
            Body body = (Body) marshallingDecoder.decode(ctx, in);
            message.setBody(body);
        }
        /*if (headerExtentsSize > 0) {
            Map<String, Object> headExtents = new LinkedHashMap<>(headerExtentsSize);
            for(int i=0;i<headerExtentsSize;i++) {
                keyLength = in.readInt();
                keyBytes = new byte[keyLength];
                in.readBytes(keyBytes);
                key = new String(keyBytes,"UTF-8");
                headExtents.put(key,marshallingDecoder.decode(ctx, in));
            }
        }*/

        keyLength = 0;
        keyBytes = null;
        key = null;

        message.setHeader(header);
        return message;
    }
}