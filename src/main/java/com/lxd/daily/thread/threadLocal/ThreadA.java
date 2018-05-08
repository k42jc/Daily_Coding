package com.lxd.daily.thread.threadLocal;

/**
 * Created by liaoxudong
 * Date:2018/5/7
 */

public class ThreadA extends Thread{

    public ThreadA(String a) {
        super(a);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.get());
        ThreadLocalHolder.setValue("新值A");
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.get());
        ThreadLocalHolder.remove();
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.get());
    }
}
