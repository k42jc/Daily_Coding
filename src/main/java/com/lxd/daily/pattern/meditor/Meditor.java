package com.lxd.daily.pattern.meditor;


/**
 * 抽象中介者
 *
 * 定义抽象交互方法，具体由子类实现
 */
public abstract class   Meditor {

    public abstract void componentChanged(Component component);
}
