package com.lxd.daily.thread.lock.read_write;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读读共享，写写互斥，读写/写读互斥
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class RWLockDemo {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    public void read(){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "获取读锁:" +System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();
    }

    /**
     * 写锁
     */
    public void write(){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "获取写锁："+ System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {
        // 读读操作，共享锁
        /*for(int i=0;i<10;i++) {
            new Thread(() -> {
                new RWLockDemo().read();// 10个线程几乎同时获取到锁
            }).start();
        }
        // 写写操作互斥
        for(int i=0;i<10;i++) {
            new Thread(() -> {
                new RWLockDemo().write();// 10个线程依次获取锁
            }).start();
        }*/

    }
}
