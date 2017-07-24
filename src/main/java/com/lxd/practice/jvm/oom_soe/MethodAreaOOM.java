package com.lxd.practice.jvm.oom_soe;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 模拟方法区内存溢出
 *
 * VM args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * Created by liaoxudong on 2017/7/17.
 */
public class MethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, args1, methodProxy) -> methodProxy.invokeSuper(o, args1));
            enhancer.create();
        }
    }

    static class OOMObject{}
}
