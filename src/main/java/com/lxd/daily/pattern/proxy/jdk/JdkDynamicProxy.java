package com.lxd.daily.pattern.proxy.jdk;

import com.lxd.daily.pattern.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liaoxudong
 * Date:2018/1/15
 */

public class JdkDynamicProxy implements InvocationHandler{

    private Object delegate;

    public Object bind(Object target) {
        this.delegate = target;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(), this.delegate.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preRequest();
        Object result = method.invoke(this.delegate, args);
        afterRequest();
        return result;
    }

    private void afterRequest() {
        System.out.println("代理后");
    }

    private void preRequest() {
        System.out.println("代理前");
    }

    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy();
        // 代理realSubject
        /*Subject subject = (Subject) jdkDynamicProxy.bind(new RealSubject());
        subject.doRequest();*/
        // 代理someSubject
        Subject someSubject = (Subject) jdkDynamicProxy.bind(new SomeSubject());
        someSubject.doRequest();
    }
}
