package com.lxd.daily.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象目标类
 */
public abstract class AllyControlCenter {
    // 战队名称
    protected String allyName;
    // 盟友战队，用于维护所有的观察者集合
    protected List<Observer> players = new ArrayList<>();

    /**
     * 注册观察者
     * @param observer
     */
    public void join(Observer observer) {
        System.out.println(observer.getName()+" 加入 "+this.allyName +" 战队");
        this.players.add(observer);
    }

    /**
     * 观察者退订
     * @param observer
     */
    public void quit(Observer observer) {
        System.out.println(observer.getName()+" 加入 "+this.allyName +" 战队");
        this.players.remove(observer);
    }
    public String getAllyName() {
        return allyName;
    }

    public void setAllyName(String allyName) {
        this.allyName = allyName;
    }

    /**
     * 抽象通知方法
     * @param name
     */
    public abstract void notify(String name);
}
