package com.lxd.daily.pattern.adapter.adaptee;

public class AESCertOperate implements CertOperate {
    @Override
    public void digist(String msg) {
        System.out.println("AES 加密操作:"+msg);
    }
}
