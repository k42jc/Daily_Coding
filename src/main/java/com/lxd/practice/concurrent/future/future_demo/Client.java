package com.lxd.practice.concurrent.future.future_demo;

/**
 * 模拟客户端调用
 * 虚拟数据即时返回
 * Created by liaoxudong on 2017/10/14.
 */
public class Client {
    public Data<String> request() {
        VitualData data = new VitualData();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData();
                System.out.println("开始组装真实数据");
                data.setRealData(realData);
            }
        }.start();
        System.out.println("直接返回虚拟数据");
        return data;
    }
}
