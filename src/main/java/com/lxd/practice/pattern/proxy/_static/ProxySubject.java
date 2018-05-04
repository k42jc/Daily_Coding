package com.lxd.practice.pattern.proxy._static;

import com.lxd.practice.pattern.proxy.Subject;

/**
 * 代理类
 * 保持目标类的引用，与目标类实现相同的接口
 * 通常会在处理前后做一些操作
 * 如AOP前后增强
 * Created by liaoxudong
 * Date:2018/1/15
 */

public class ProxySubject implements Subject {

    private RealSubject realSubject = new RealSubject();
    @Override
    public void doRequest() {
        preRequest();
        realSubject.doRequest();
        afterRequest();

    }

    private void afterRequest() {
        System.out.println("代理后");
    }

    private void preRequest() {
        System.out.println("代理前");
    }

    public static void main(String[] args) {
        ProxySubject proxySubject = new ProxySubject();
        proxySubject.doRequest();
    }
}
