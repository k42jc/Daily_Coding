package com.lxd.daily.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock搭配Condition实现生产者/消费者
 *
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class ConditionDemo {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition conditon = lock.newCondition();
    private static volatile boolean isA = true;

    static class ThreadA extends Thread{
        @Override
        public void run() {
            try {
                lock.lock();// 线程A获取锁
                while (!isA) {// 如果不是A打印的时候，A线程等待
                    conditon.await();
                }
                System.out.println("A");// 否则打印A
                isA = false;//然后将"打印A"赋值为false
                conditon.signalAll();// 唤醒所有线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
            try {
                lock.lock();// 线程A获取锁
                while (isA) {// 如果是打印A的时候，B线程等待
                    conditon.await();
                }
                System.out.println("B");// 否则直接打印B
                isA = true;// 将
                conditon.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }


    public static void main(String[] args) {
        for (int i=0;i<10;i++) {
            new ThreadA().start();
            new ThreadB().start();
        }
    }
}
