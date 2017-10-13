package com.lxd.practice.netty.custom.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Netty自定义协议消息体
 * Created by liaoxudong on 2017/8/1.
 */
public class Body implements Serializable{

    /**
     * 请求消息内容
     */
    private String message;

    /**
     * 请求时间
     */
    private String dateTime;

    /**
     * 消息体扩展域
     */
    private Map<String,Object> extents = new LinkedHashMap<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Map<String, Object> getExtents() {
        return extents;
    }

    public void setExtents(Map<String, Object> extents) {
        this.extents = extents;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
