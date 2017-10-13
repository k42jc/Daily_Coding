package com.lxd.practice.concurrent.thread;

/**
 * Created by liaoxudong on 2017/8/29.
 */
public class DeadLockDemo{
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void deadlock(){
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                synchronized (lock1){
                    try{Thread.sleep(2000);}catch(Exception e){e.printStackTrace();}
                    synchronized(lock2){//doSomething
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run(){
                synchronized (lock2){
                    synchronized(lock1){//doSomething
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        };
        thread1.start();
        thread2.start();

    }

    public static void main(String[] args) {
        new DeadLockDemo().deadlock();
    }
}
