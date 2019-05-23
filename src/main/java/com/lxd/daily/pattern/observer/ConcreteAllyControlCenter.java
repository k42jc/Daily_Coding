package com.lxd.daily.pattern.observer;

/**
 * 具体目标类，实际战队指挥中心
 */
public class ConcreteAllyControlCenter extends AllyControlCenter {

    public ConcreteAllyControlCenter(String allyName) {
        System.out.println(allyName + " 战队组建成功");
        this.allyName = allyName;
    }

    @Override
    public void notify(String name) {
        System.out.println(this.allyName + " 战队紧急通知，盟友 "+name+ " 受到攻击！");
        // 通知除自身外所有观察者
        for (Observer observer : players) {
            if(!observer.getName().equalsIgnoreCase(name))
                observer.help();
        }
    }
}
