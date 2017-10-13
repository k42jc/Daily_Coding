package com.lxd.practice.concurrent.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示Lock condition多路通知
 * Created by liaoxudong on 2017/10/14.
 */
public class LockConditionDemo {

    private static Lock lock = new ReentrantLock();
    private static Condition cond1 = lock.newCondition();
    private static Condition cond2 = lock.newCondition();
    private static Condition cond3 = lock.newCondition();
    private static Condition cond4 = lock.newCondition();

    private static volatile int target;

    public static void main(String[] args) {
        demo(1);
    }

    /**
     * 演示多个不同的线程根据target配置输出不同的序列，每个线程只能输出一个字符
     * 根据输入参数不同【1、2、3、4】(勿传入其它数，会导致所有线程阻塞)
     * 会分别输入ABCD、BCDA、CDBA、DABC
     */
    public static void demo(int param){
        target = param;
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {//为了明显期间，给一个循环
                        while (target != 1) {
                            cond1.await();
                        }
                        System.out.print("A");
                        target = 2;
                        cond2.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {//为了明显期间，给一个循环
                        while (target != 2) {
                            cond2.await();
                        }
                        System.out.print("B");
                        target = 3;
                        cond3.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {//为了明显期间，给一个循环
                        while (target != 3) {
                            cond3.await();
                        }
                        System.out.print("C");
                        target = 4;
                        cond4.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread thread4 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {//为了明显期间，给一个循环
                        while (target != 4) {
                            cond4.await();
                        }
                        System.out.print("D");
                        target = 1;
                        cond1.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
