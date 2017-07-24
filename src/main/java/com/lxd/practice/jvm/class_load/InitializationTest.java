package com.lxd.practice.jvm.class_load;

/**
 * <b>简单演示类初始化过程静态代码块可以对后面定义的变量赋值，但不能访问</b>
 *
 * 下面类的赋值语句可以正确编译，输出语句却不能通过编译
 * Created by liaoxudong on 2017/7/20.
 */
public class InitializationTest {
    static{
        i = 1;
//        System.out.println(i);
    }

    static int i = 4;
}

class Parent{
    static int A = 1;

    static {
        A =2;
    }
}

class Sub extends Parent{
    public static int B = A;

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
