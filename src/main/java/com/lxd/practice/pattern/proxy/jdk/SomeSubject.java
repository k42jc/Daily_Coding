package com.lxd.practice.pattern.proxy.jdk;

import com.lxd.practice.pattern.proxy.Subject;

/**
 * 被代理类
 * Created by liaoxudong
 * Date:2018/1/15
 */

public class SomeSubject implements Subject {
    @Override
    public void doRequest() {
        System.out.println("做点其它东西");
    }
}
