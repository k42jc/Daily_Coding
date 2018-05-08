package com.lxd.daily.netty.custom.codec;

import com.lxd.daily.exception.ExceptionCode;
import com.lxd.daily.exception.PracticeException;
import com.lxd.daily.netty.custom.pojo.Header;
import com.lxd.daily.netty.custom.pojo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Netty自定义协议消息编码器
 * Created by liaoxudong on 2017/8/1.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>{

    CustomMarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() {
        this.marshallingEncoder = new CustomMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage nettyMessage, List<Object> out) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new PracticeException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        String key = null;
        byte[] keyBytes = null;
        Object value = null;
        ByteBuf sendBuf = Unpooled.buffer();
//        // 报文长度占位
//        sendBuf.writeInt(0);
        // 编码时按此顺序写 解码时需要按次顺序读

        // 写入消息头
        Header header = nettyMessage.getHeader();

        sendBuf.writeInt(header.getCrcCode());
        sendBuf.writeInt(header.getLength());
        sendBuf.writeByte(header.getPriority());
        sendBuf.writeByte(header.getType());
        sendBuf.writeLong(header.getSessionID());
        /*sendBuf.writeInt(header.getExtents().size());
        for (Map.Entry<String,Object> entry : header.getExtents().entrySet()) {
            key = entry.getKey();
            keyBytes = key.getBytes("UTF-8");
            sendBuf.writeInt(keyBytes.length);
            sendBuf.writeBytes(keyBytes);
            value = entry.getValue();
            marshallingEncoder.encode(ctx,value,sendBuf);
        }*/

        if(nettyMessage.getBody() != null){
            // 作为有消息体的标识
            sendBuf.writeInt(1);
            // 消息体直接使用marshalling编码
            marshallingEncoder.encode(ctx,nettyMessage.getBody(),sendBuf);
            /*// 写入消息体
            Body body = nettyMessage.getBody();

            String message = body.getMessage();
            if (message != null) {
                byte[] bytes = message.getBytes("UTF-8");
                sendBuf.writeInt(bytes.length);
                sendBuf.writeBytes(bytes);
            }

            String dateTime = body.getDateTime();
            if (dateTime != null) {
                byte[] dateTimeBytes = dateTime.getBytes("UTF-8");
                sendBuf.writeInt(dateTimeBytes.length);
                sendBuf.writeBytes(dateTimeBytes);
            }

            sendBuf.writeInt(body.getExtents().size());
            for (Map.Entry<String,Object> entry : body.getExtents().entrySet()) {
                key = entry.getKey();
                keyBytes = key.getBytes("UTF-8");
                sendBuf.writeInt(keyBytes.length);
                sendBuf.writeBytes(keyBytes);
                value = entry.getValue();
                marshallingEncoder.encode(ctx,value,sendBuf);
            }*/
        }else{
            sendBuf.writeInt(0);
        }
        // 设置请求报文长度，偏移4，占4位，长度需要减去偏移量之和(8) 用于LengthFieldBasedFrameDecoder半包读写的报文长度确定
        sendBuf.setInt(4, sendBuf.readableBytes()-8);

        key = null;
        keyBytes = null;
        value = null;

        out.add(sendBuf);
    }
}
