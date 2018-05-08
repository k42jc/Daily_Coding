package com.lxd.daily.thread.lock;

/**
 * 非公平锁：线程获取锁的顺序与加锁的顺序不一定相同
 * 也就是说：先进入run方法的线程不一定会先获取锁
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class NotFairLock {

    public static void main(String[] args) {
        FairLock notFairLock = new FairLock(false);
        for(int i=0;i<10;i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "==开始执行");
                notFairLock.start();
            }).start();
        }
    }
}
