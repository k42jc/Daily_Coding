package com.lxd.daily.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试可重入锁(lock机制探索)
 */
public class ReentrantLockDemo {

    private static final ReentrantLock lock = new ReentrantLock(false);

    private static class WorkThread extends Thread{
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
//                Thread.sleep(1000);
            }catch (Exception e){}
            finally {
                lock.unlock();
            }
        }

        public WorkThread(String name) {
            super(name);
        }
    }

    public static void main(String[] args){
        for(int i=0;i<100;i++) {
            new WorkThread("thread" + i).start();
        }

    }
}
