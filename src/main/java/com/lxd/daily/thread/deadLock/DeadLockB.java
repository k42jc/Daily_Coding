package com.lxd.daily.thread.deadLock;

/**
 * 死锁演示2：某个线程由于种种原因(异常/死循环等)无法成功释放锁，导致其它线程永久阻塞
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class DeadLockB {
    private static final String lock = "lock";

    static class ThreadA extends Thread{
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("线程A获取到锁，不释放");
                while (true) {
                    //线程A永不释放锁
                }
            }
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("线程B获取锁");
            }
            System.out.println("线程B释放锁");
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<20;i++) {
            new ThreadB().start();// 让线程B先跑，方便看到线程B释放锁之后如果由A拿到锁，则A不释放锁导致B永远处于Blocked状态导致的死锁
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new ThreadA().start();
        }
    }
}
