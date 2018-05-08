package com.lxd.daily.thread.deadLock;

import com.lxd.daily.util.JStackUtils;

/**
 * 演示死锁：互相等待
 * 两个个线程交叉持有"两把锁"的情况，在A线程获取到锁A后，继续获取锁B。此时锁B由B线程持有并未释放，且B线程正试图去获取锁A
 * 结果便是两个线程属于互相阻塞，等待对方释放锁，引起死锁
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class DeadLockA {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (lockA){
                System.out.println("线程A获取到lockA");
                synchronized (lockB) {
                    System.out.println("线程A继续获取到lockB");
                }
            }
        }
    }

    static class ThreadB extends Thread{

        public ThreadB(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (lockB) {
                System.out.println("线程B获取到lockB");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.println("线程B继续获取到lockA");
                }
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<20;i++) {
            new ThreadA("A").start();
            new ThreadB("B").start();
            String javaStackTrace = JStackUtils.getJavaStackTrace();
            System.out.println(javaStackTrace);
        }
    }
}
