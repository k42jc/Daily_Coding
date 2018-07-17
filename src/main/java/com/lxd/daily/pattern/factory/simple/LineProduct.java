package com.lxd.daily.pattern.factory.simple;

/**
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class LineProduct extends Product{

    public LineProduct() {
        System.out.println("创建折线图");
    }

    @Override
    void display() {
        System.out.println("显示折线图");
    }
}
