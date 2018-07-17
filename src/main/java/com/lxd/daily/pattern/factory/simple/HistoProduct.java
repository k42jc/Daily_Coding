package com.lxd.daily.pattern.factory.simple;

/**
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class HistoProduct extends Product{

    public HistoProduct(){
        System.out.println("创建柱状图实例");
    }
    @Override
    void display() {
        System.out.println("显示柱状图");
    }
}
