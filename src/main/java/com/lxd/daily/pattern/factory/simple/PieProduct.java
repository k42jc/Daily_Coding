package com.lxd.daily.pattern.factory.simple;

/**
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class PieProduct extends Product{

    public PieProduct() {
        System.out.println("创建饼状图实例");
    }

    @Override
    void display() {
        System.out.println("显示饼状图");
    }
}
