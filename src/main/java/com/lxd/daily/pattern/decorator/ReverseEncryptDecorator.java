package com.lxd.daily.pattern.decorator;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class ReverseEncryptDecorator extends Decorator{

    public ReverseEncryptDecorator(Encrypt encrypt) {
        super(encrypt);
    }

    @Override
    protected void beforeEncrypt(String msg) {

    }

    @Override
    protected void afterEncrypt(String msg) {
        System.out.println("再次进行逆向输出加密："+msg);
    }
}
