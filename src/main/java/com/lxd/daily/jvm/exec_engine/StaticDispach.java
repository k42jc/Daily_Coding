package com.lxd.daily.jvm.exec_engine;

/**
 * <b>方法静态分派演示</b>
 *
 * 执行以下类输出：
 * <pre>
 *     human say hello!
 *     human say hello!
 * </pre>
 * <br><br>
 * 由于重载方法是通过参数的静态类型作为依据来调用具体方法的
 * Created by liaoxudong on 2017/7/24.
 */
public class StaticDispach {

    static abstract class Human{

    }

    static class Man extends Human{

    }

    static class Women extends Human{

    }

    public void sayHello(Human human) {
        System.out.println("human say hello!");
    }

    public void sayHello(Man man) {
        System.out.println("man say hello!");
    }

    public void sayHello(Women women) {
        System.out.println("women say hello!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();

        StaticDispach staticDispach = new StaticDispach();
        staticDispach.sayHello(man);
        staticDispach.sayHello(women);
    }
}
