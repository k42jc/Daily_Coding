package com.lxd.practice.rmi.client;

import com.lxd.practice.rmi.server.HelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 调用RMI服务
 * Created by liaoxudong on 2017/8/8.
 */
public class HelloClient {

    public static void main(String[] args) {
        String uri = "rmi://localhost:1099/helloService";
        try {
            // 使用Naming发现服务
            Remote lookup = Naming.lookup(uri);
            HelloService helloService = (HelloService)lookup;
            helloService.sayHello("张三");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
