package com.lxd.daily.pattern.observer;

/**
 * 观察者模式客户端调用示例
 */
public class Client {

    public static void main(String[] args){
        // 定义观察目标，控制中心
        AllyControlCenter acc = new ConcreteAllyControlCenter("alibaba战队控制中心");

        // 定义观察者，具体战队
        Observer taobao = new Player("淘宝战队");
        Observer aliexpress = new Player("速卖通战队");
        Observer cainiao = new Player("菜鸟战队");
        Observer mayijinfu = new Player("蚂蚁金服战队");

        acc.join(taobao);
        acc.join(aliexpress);
        acc.join(cainiao);
        acc.join(mayijinfu);

        // 某一战队遭受攻击
        taobao.beAttached(acc);
    }
}
