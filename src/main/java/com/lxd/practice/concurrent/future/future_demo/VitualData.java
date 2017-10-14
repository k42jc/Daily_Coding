package com.lxd.practice.concurrent.future.future_demo;

/**
 * Created by liaoxudong on 2017/10/14.
 */
public class VitualData implements Data<String> {
    //包装真实数据
    private RealData realData = null;
    //设定一个真实数据是否组装完成的标记
    private volatile boolean isFinish = false;

    public synchronized void setRealData(RealData data) {
        this.realData = data;
        isFinish = true;
        System.out.println("真实数据组装完毕！");
        notifyAll();
    }
    @Override
    public synchronized String getResult() {
        while (!isFinish) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
