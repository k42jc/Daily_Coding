package com.lxd.daily.jvm.exec_engine;

/**
 * <b>重载方法匹配优先级演示</b>
 *
 * 运行以下类输出 'hello char'
 * <br>
 * 如果依次注释掉方法，输出优先级分别为：<i>int -> long -> float -> double -> Character -> Serializable -> Object -> char...</i>
 *
 * 不会输出String，char类型无法通过隐式/显示转换为String
 * Created by liaoxudong on 2017/7/24.
 */
public class Overload {

    public static void sayHello(Object arg){
        System.out.println("hello object");
    }

    /*public static void sayHello(Serializable arg){
        System.out.println("hello Serializable");
    }*/

    /*public static void sayHello(char arg){
        System.out.println("hello char");
    }*/

    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }

    /*public static void sayHello(Character arg){
        System.out.println("hello Character");
    }*/

    /*public static void sayHello(int arg){
        System.out.println("hello int");
    }*/

    /*public static void sayHello(long arg){
        System.out.println("hello long");
    }*/

    /*public static void sayHello(float arg){
        System.out.println("hello float");
    }*/

    /*public static void sayHello(double arg){
        System.out.println("hello double");
    }*/

    public static void sayHello(String arg){
        System.out.println("hello String");
    }

    public static void main(String[] args) {
        Overload overload = new Overload();
        overload.sayHello('a');
    }
}
