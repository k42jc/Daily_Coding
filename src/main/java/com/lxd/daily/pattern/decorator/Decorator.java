package com.lxd.daily.pattern.decorator;

/**
 * 抽象装饰器
 *
 * 维护具体加密对象的引用，对加密方法的调用实际调用目标对象的加密操作
 *
 * 定义两个模板方法用于子类对目标加密对象的【装饰操作】
 * Created by liaoxudong
 * Date:2018/7/19
 */

public abstract class Decorator implements Encrypt {
    private Encrypt encrypt;

    public Decorator(Encrypt encrypt) {
        this.encrypt = encrypt;
    }

    @Override
    public void encrypt(String msg) {
        beforeEncrypt(msg);
        this.encrypt.encrypt(msg);
        afterEncrypt(msg);
    }

    protected abstract void beforeEncrypt(String msg);

    protected abstract void afterEncrypt(String msg);
}
