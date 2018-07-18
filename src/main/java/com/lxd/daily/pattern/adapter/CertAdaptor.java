package com.lxd.daily.pattern.adapter;

import com.lxd.daily.pattern.adapter.adaptee.CertOperate;
import com.lxd.daily.pattern.adapter.target.DBOperate;

/**
 * 加密操作适配器，实现目标接口，且维护加密操作的引用，实际加密操作由具体加密类完成
 */
public class CertAdaptor implements DBOperate{
    private CertOperate certOperate;

    public CertAdaptor(CertOperate certOperate) {
        this.certOperate = certOperate;
    }

    @Override
    public void addUser(String username, String pwd) {
        this.certOperate.digist(pwd);
    }
}
