package com.lxd.practice.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 提供RMI接口的具体实现，必须要继承UnicastRemoteObject类，并提供默认构造器且抛出java.rmi.RemoteException
 * Created by liaoxudong on 2017/8/8.
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{
    public HelloServiceImpl() throws RemoteException {
    }

    @Override
    public void sayHello(String name) throws RemoteException {
        System.out.println("hello "+name);
    }
}
