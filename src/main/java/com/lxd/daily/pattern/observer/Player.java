package com.lxd.daily.pattern.observer;

/**
 * 具体观察者，所有玩家，包括自己
 */
public class Player implements Observer {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 事件响应实现
     */
    @Override
    public void help() {
        System.out.println("坚持住，"+this.name + " 正在赶过来救你");
    }

    /**
     * 通知其它观察者实现
     * @param acc
     */
    @Override
    public void beAttached(AllyControlCenter acc) {
        System.out.println(this.name + " 被攻击！");
        acc.notify(this.name);
    }
}
