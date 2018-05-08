package com.lxd.daily.jvm.cmd_bin;

/**
 * <b>演示死锁，并使用监控工具查看</b>
 * Created by liaoxudong on 2017/7/21.
 */
public class DeadLockDemo {

    private final static Object lock1 = new Object();
    private final static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + " lock1");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + " lock2");
                    }
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + " lock2");
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + " lock1");
                    }
                }
            }
        };

        thread1.start();
        thread2.start();

    }
}
