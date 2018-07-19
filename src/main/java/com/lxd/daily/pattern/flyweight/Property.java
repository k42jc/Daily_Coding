package com.lxd.daily.pattern.flyweight;

/**
 * 多媒体文件属性
 *
 * 保存位置、文件大小
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class Property {

    private String position;
    private int size;

    public Property(String position, int size) {
        this.position = position;
        this.size = size;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "位置："+this.position + ",大小：" + this.size;
    }
}
