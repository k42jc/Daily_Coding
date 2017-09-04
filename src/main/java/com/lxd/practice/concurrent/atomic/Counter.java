package com.lxd.practice.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现线程安全计数器与非线程安全计数器
 * Created by liaoxudong on 2017/8/30.
 */
public class Counter {

    private AtomicInteger atomicIt = new AtomicInteger(0);
    private int it = 0;

    /**
     * 线程安全计数器
     */
    public void safCount(){
        // 使用自旋CAS
        /*for(;;) {
            int i = atomicIt.get();*/
            atomicIt.getAndIncrement();
            /*boolean b = atomicIt.compareAndSet(i, i + 1);
            if (b) {
                break;
            }
        }*/
    }

    /**
     * 非线程安全计数器
     */
    public void count(){
        it++;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        List<Thread> threads = new ArrayList<>(120);
        for(int i=0;i<100;i++) {
            threads.add(new Thread(){
                @Override
                public void run() {
                    for(int j=0;j<10000;j++) {
                        counter.count();
                        counter.safCount();
                    }
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        //等待线程完成
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter.it);
        System.out.println(counter.atomicIt.get());
    }
}
