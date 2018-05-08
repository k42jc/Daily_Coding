package com.lxd.daily.exception;

/**
 * 自定义异常
 * Created by liaoxudong on 2017/7/28.
 */
public class PracticeException extends RuntimeException{

    public PracticeException(ExceptionCode code) {
        super(code.getCode()+"："+code.getDesc());
    }

    public PracticeException(ExceptionCode code, Throwable cause) {
        super(code.getCode()+"："+code.getDesc(), cause);
    }
}
