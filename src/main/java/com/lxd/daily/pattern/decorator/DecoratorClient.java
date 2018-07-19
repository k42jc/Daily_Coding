package com.lxd.daily.pattern.decorator;

/**
 *
 * 装饰器模式客户端操作
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class DecoratorClient {

    public static void main(String[] args) {
        Encrypt simpleEncrypt = new SimpleEncrypt();
        simpleEncrypt.encrypt("xxxxx");
        Encrypt replaceEncrypt = new ReplaceEncryptDecorator(simpleEncrypt);
        replaceEncrypt.encrypt("xxxxx");
        Encrypt encryptDecorator = new ReverseEncryptDecorator(replaceEncrypt);
        encryptDecorator.encrypt("xxxxx");
    }
}
