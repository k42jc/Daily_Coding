package com.lxd.daily.pattern.observer;

/**
 * 抽象观察者
 */
public interface Observer {

    String getName();

    void setName(String name);

    /**
     * 申明支援盟友的方法
     */
    void help();

    /**
     * 申明遭受攻击方法
     * @param acc
     */
    void beAttached(AllyControlCenter acc);
}
