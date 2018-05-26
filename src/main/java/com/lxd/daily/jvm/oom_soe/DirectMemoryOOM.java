package com.lxd.daily.jvm.oom_soe;

//import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 模拟本机直接内存溢出
 * VM args:-Xmx20M -XX:MaxDirectMemorySize=10M
 * Created by liaoxudong on 2017/7/17.
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
//        Field field = Unsafe.class.getDeclaredFields()[0];

//        field.setAccessible(true);
//        Unsafe unsafe = (Unsafe) field.get(null);
//        while (true) {
//            unsafe.allocateMemory(_1MB);
//        }
    }
}
