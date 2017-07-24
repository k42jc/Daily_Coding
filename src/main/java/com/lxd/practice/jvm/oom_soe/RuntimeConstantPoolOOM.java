package com.lxd.practice.jvm.oom_soe;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟运行时常量池的内存溢出异常
 *
 * 运行时常量池是方法区(Permanent Generation Space -- 永久带空间)的一部分
 *
 * jdk1.6及之前版本，通过 -XX:PermSize和-XX:MaxPermSize 限制方法区的大小从而间接限制其中常量池的容量
 * 本程序只能在1.6及之前版本有效果，1.7之后运行循环会一直进行下去
 *
 * VM args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * Created by liaoxudong on 2017/7/17.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
//        testRuntimeConstantPoolString();
        //使用List保存着常量池引用，避免Full GC回收常量池的行为
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true) {
            // String.intern()是一个native方法，作用是：如果字符串常量池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象
            // 否则，将此String对象包含的字符串添加到常量池中，且返回此String对象的引用
            list.add(String.valueOf(i++).intern());
        }
    }


    /**
     * 下面方法在1.7之前返回<false,false>，1.7之后返回<true,false>
     */
    public static void testRuntimeConstantPoolString(){
        String s = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s.intern() == s);

        String s1 = new StringBuilder("ja").append("va").toString();
        System.out.println(s1.intern() == s1);
    }
}
