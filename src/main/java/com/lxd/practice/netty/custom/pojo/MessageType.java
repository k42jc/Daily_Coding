package com.lxd.practice.netty.custom.pojo;

/**
 * 消息请求类型
 * Created by liaoxudong on 2017/8/1.
 */
public enum MessageType {
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

    REQ((byte)0, "请求消息"),
    RESP((byte)1,"响应消息"),
    ONE_WAY((byte)2,"ONE WAY消息(既是请求消息又是响应消息)"),
    SHAKE_HAND_REQ((byte)3,"握手请求"),
    SHAKE_HAND_RESP((byte)4,"握手应答"),
    HEART_BEAT_REQ((byte)5,"心跳请求"),
    HEART_BEAT_RESP((byte)6,"心跳响应"),
    ;

    private byte code;
    private String desc;

    MessageType(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
