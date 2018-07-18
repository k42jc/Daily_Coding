package com.lxd.daily.pattern.adapter.adaptee;

public class RSACertOperate implements CertOperate {
    @Override
    public void digist(String msg) {
        System.out.println("RSA 加密操作："+msg);
    }
}
