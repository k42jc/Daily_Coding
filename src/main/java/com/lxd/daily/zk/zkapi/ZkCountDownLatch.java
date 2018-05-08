package com.lxd.daily.zk.zkapi;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liaoxudong on 2017/8/15.
 */
public class ZkCountDownLatch extends CountDownLatch{
    private Object data;
    public ZkCountDownLatch(int count) {
        super(count);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
