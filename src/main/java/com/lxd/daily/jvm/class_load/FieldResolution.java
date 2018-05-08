package com.lxd.daily.jvm.class_load;

/**
 * <b>演示解析过程的字段解析</b>
 *
 * <p>
 *     如果将Sub类中的
 *     <pre>
 *         public static int A = 4;
 *     </pre>
 *     注释，则编译器会拒绝执行，因为A字段在Sub的父类与接口以及顶级接口同名，导致变量混淆
 * </p>
 *
 * Created by liaoxudong on 2017/7/20.
 */
public class FieldResolution {

    interface Interface0{
        int A = 0;
    }

    interface Interface1 extends Interface0{
        int A = 1;
    }

    interface Interface2{
        int A = 2;
    }

    static class Parent implements Interface1{
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2{
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
