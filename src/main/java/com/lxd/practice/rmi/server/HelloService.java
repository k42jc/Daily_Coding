package com.lxd.practice.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 提供远程调用接口，必须继承java.rmi.Rmote接口，且方法一定要抛出RemoteException
 * Created by liaoxudong on 2017/8/8.
 */
public interface HelloService extends Remote{
    void sayHello(String name) throws RemoteException;
}
