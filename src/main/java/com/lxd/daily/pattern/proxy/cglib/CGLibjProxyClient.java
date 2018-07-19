package com.lxd.daily.pattern.proxy.cglib;

/**
 * cglib动态代理调用示例
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class CGLibjProxyClient {

    public static void main(String[] args) {
        SubProxy proxy = new SubProxy();
        Target subTarget = (Target)proxy.getProxy(Target.class);
        subTarget.display();
    }
}
