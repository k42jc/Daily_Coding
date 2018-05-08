package com.lxd.daily.jvm.exec_engine;

/**
 *
 * <b>类的静态、构造器、私有方法以及final修饰的方法在解析阶段就已经确定唯一调用版本，称为非虚方法</b>
 * Created by liaoxudong on 2017/7/24.
 */
public class Static_private_demo {

    static class InnerDmeo{
        static void test(){
            System.out.println("hello world!");
        }
    }

    static class Demo extends InnerDmeo{
        // 不能重写
        /*void test(){
            System.out.println("heeeeello world!");
        }*/
    }
}
