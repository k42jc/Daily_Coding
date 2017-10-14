package com.lxd.practice.concurrent.future.future_demo;

/**
 * Future模式数据接口
 * Created by liaoxudong on 2017/10/14.
 */
public interface Data<T> {

    T getResult();
}
