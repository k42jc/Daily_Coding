package com.lxd.daily.netty.delimiter;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TCP连接信息封装
 * Created by liaoxudong on 2017/7/28.
 */
public class ClientMsg {

    private String host;
    private int port;
    private Object sendMsg;

    public ClientMsg(String host, int port, Object sendMsg) {
        this.host = host;
        this.port = port;
        this.sendMsg = sendMsg;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Object getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(Object sendMsg) {
        this.sendMsg = sendMsg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
