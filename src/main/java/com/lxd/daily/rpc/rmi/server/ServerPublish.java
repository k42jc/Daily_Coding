package com.lxd.daily.rpc.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 发布RMI服务
 * Created by liaoxudong on 2017/8/8.
 */
public class ServerPublish {

    public static void main(String[] args) {
        String uri = "rmi://localhost:1099/helloService";

        try {
            // 使用LocateRegistry.createRegistry在JNDI中创建注册表，设置端口
            LocateRegistry.createRegistry(1099);
            // 使用Naming绑定调用地址与具体实现
            Naming.rebind(uri,new HelloServiceImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
