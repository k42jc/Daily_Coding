package com.lxd.daily.structure;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author liaoxudong
 * @date 2019/5/13 23:39
 */
public class Deque {

    public static void main(String[] args){
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.offer("a");
        queue.offer("b");
        for(int i=1;i<10;i++) {
            final int x = i;
            new Thread(() -> queue.offer("" + x)).start();
        }
    }
}
