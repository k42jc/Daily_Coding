package com.lxd.daily.pattern.factory.abstra.button;

/**
 * 具体风格的按钮
 * Created by liaoxudong
 * Date:2018/7/17
 */

public class SpringButton implements Button{
    @Override
    public void display() {
        System.out.println("绿色按钮");
    }
}
