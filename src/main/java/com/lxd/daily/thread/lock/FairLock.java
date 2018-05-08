package com.lxd.daily.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁:线程获取锁的顺序与对线程加锁顺序一致，也就是先执行run方法的线程先获取锁
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class FairLock {
    private final ReentrantLock lock;

    public FairLock(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void start(){
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ">获取锁");
        lock.unlock();
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock(true);
        for(int i=0;i<10;i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "==我开始执行");
                fairLock.start();
            }).start();
        }


    }
}
