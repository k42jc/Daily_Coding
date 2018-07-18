package com.lxd.daily.pattern.adapter;

import com.lxd.daily.pattern.adapter.adaptee.RSACertOperate;
import com.lxd.daily.pattern.adapter.target.DBOperate;

public class AdaptorClient {

    public static void main(String[] args){
        DBOperate dbOperate = new CertAdaptor(new RSACertOperate());
        dbOperate.addUser("username","password");
    }
}
