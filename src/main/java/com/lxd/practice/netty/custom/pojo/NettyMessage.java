package com.lxd.practice.netty.custom.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 自定义协议报文结构定义
 * <br>
 * Created by liaoxudong on 2017/8/1.
 */
public class NettyMessage {
    private Header header;
    private Body body;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
