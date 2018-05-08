package com.lxd.daily.exception;

/**
 * 异常标识
 * Created by liaoxudong on 2017/7/28.
 */
public enum ExceptionCode {

    DEFAULT_EXCEPTION("00","默认异常"),
    ILLEGAL_PARAMETER("01","参数有误"),
    TCP_CONNECT_TIME_OUT("02","TCP连接超时!"),
    ;

    private String code;
    private String desc;

    ExceptionCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
