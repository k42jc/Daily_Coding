package com.lxd.daily.pattern.flyweight;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class Text implements Media{
    @Override
    public void show(Property property) {
        System.out.println("文本文件："+property);
    }
}
