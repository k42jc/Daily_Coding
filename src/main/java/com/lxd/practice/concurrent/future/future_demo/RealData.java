package com.lxd.practice.concurrent.future.future_demo;

/**
 * 实际组装数据模拟
 * Created by liaoxudong on 2017/10/14.
 */
public class RealData implements Data<String>{

    @Override
    public String getResult() {
        // 模拟数组组装过程，通常这是一个缓慢的过程，可以通过Thread.sleep()模拟
        StringBuilder builder = new StringBuilder("hello world!");
        for(int i=0;i<5;i++) {
            builder.append(" "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
