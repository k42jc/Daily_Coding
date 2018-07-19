package com.lxd.daily.pattern.composite;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class Button implements Controls{
    @Override
    public void display() {
        System.out.println("按钮显示");
    }
}
