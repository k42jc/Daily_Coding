package com.lxd.practice.concurrent.netty.delimiter;

import java.util.concurrent.CountDownLatch;

/**
 * 用于Netty Client请求同步返回数据
 * Created by liaoxudong on 2017/7/28.
 */
public class ClientCountDownLatch extends CountDownLatch{
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ClientCountDownLatch(int count) {
        super(count);
    }
}
