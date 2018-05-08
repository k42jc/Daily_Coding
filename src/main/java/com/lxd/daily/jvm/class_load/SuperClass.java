package com.lxd.daily.jvm.class_load;

/**
 * <h3>用于演示类加载</h3>
 *
 * 执行main方法
 * 其中演示1只会输出：
 * <pre>
 *     SuperClass init!
 *     123
 * </pre>
 * <b>演示2、3不会输出任何东西！</b>
 * <br>
 * <p>
 * 1). 对静态字段的引用只会触发直接定义这个字段的类才会初始化，因此通过其子类来引用父类的静态字段只会触发父类的初始化
 * 2). 通过数组定义来引用类，不会触发当前类的初始化
 * 3). 引用类的常量不会触发类的初始化
 * </p>
 *
 * Created by liaoxudong on 2017/7/20.
 */
public class SuperClass {

    static{
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

class SubClass extends SuperClass{
    static{
        System.out.println("SubClass init!");
    }

    public final static String CONSTANTS_1 = "SubClass Constants";
}


class NotInitialization{
    public static void main(String[] args) {
        // 演示1 ：使用子类引用父类的静态字段只会触发父类的初始化
        /*System.out.println(SubClass.value);*/
        // 演示2 ：通过数组定义来引用类，不会触发此类的初始化
        /*SuperClass[] superClasses = new SuperClass[10];*/
        // 演示3 ：对常量的引用不会触发类的初始化，常量在编译期间会存入调用类的常量池
        System.out.println(SubClass.CONSTANTS_1);
    }
}
