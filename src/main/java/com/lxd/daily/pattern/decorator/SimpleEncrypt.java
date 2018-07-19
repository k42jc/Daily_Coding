package com.lxd.daily.pattern.decorator;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class SimpleEncrypt implements Encrypt {

    @Override
    public void encrypt(String msg) {
        System.out.println("简单加密："+msg);
    }
}
