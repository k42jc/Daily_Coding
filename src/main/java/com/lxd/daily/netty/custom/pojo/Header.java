package com.lxd.daily.netty.custom.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 自定义协议请求头
 * Created by liaoxudong on 2017/8/1.
 */
public class Header {

    // 消息校验码 固定值(0xABEF)+主版本号+次版本号
    private int crcCode = 0xabef0101;
    // 消息长度 报文整体长度
    private int length;
    //  回话id 全局唯一
    private long sessionID;
    /**
     * 请求类型
     * 0-请求消息
     * 1-响应消息
     * 2-ONE WAY消息(既是请求消息又是响应消息)
     * 3-握手请求
     * 4-握手应答
     * 5-心跳请求
     * 6-心跳响应
     */
    private byte type;
    // 请求优先级
    private byte priority;

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
