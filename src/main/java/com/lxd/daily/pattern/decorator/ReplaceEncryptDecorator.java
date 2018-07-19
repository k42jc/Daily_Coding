package com.lxd.daily.pattern.decorator;

/**
 * 移位加密装饰器
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class ReplaceEncryptDecorator extends Decorator{

    public ReplaceEncryptDecorator(Encrypt encrypt) {
        super(encrypt);
    }

    @Override
    protected void beforeEncrypt(String msg) {

    }

    @Override
    protected void afterEncrypt(String msg) {
        System.out.println("再次使用移位加密："+msg);
    }
}
