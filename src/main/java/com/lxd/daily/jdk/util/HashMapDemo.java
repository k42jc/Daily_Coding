package com.lxd.daily.jdk.util;


import java.util.HashMap;

/**
 * HashMap实现原理探究
 */
public class HashMapDemo {

    public static void main(String[] args){
        HashMap<Integer, Integer> map = new HashMap<>(7);
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.put(4, 1);
        map.put(5, 1);
        map.put(6, 1);
        map.put(7, 1);
        map.put(8, 1);
        map.put(9, 1);
        map.put(10, 1);
        map.put(11, 1);
        map.put(12, 1);
        map.put(13, 1);
    }
}
