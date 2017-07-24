package com.lxd.practice.jvm.exec_engine;

/**
 * <b>演示类变量会默认赋初始值，局部变量需要明确的赋值才可食用</b>
 *
 * <br>
 * <p>类加载过程中会对类变量赋予系统初始值</p>
 * Created by liaoxudong on 2017/7/24.
 */
public class TestDemo {

    private static int i;

    public static void main(String[] args) {
        System.out.println(i);
//        int j;
//        System.out.println(j);
    }
}
