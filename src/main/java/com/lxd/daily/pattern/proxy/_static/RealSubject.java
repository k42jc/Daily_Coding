package com.lxd.daily.pattern.proxy._static;

import com.lxd.daily.pattern.proxy.Subject;

/**
 * 真正处理的类，也是被代理的目标类
 * Created by liaoxudong
 * Date:2018/1/15
 */

public class RealSubject implements Subject {
    @Override
    public void doRequest() {
        System.out.println("真实处理");
    }
}
