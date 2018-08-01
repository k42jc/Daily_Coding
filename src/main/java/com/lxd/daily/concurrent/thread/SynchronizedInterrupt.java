package com.lxd.daily.concurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试synchronized是否能被interrupt中断释放锁<br/><br/>
 * <p>
 * syncInterruptTest()方法如果不将/********************下面的注释打开，thread2是永远都拿不到锁的<br/><br/>
 * <p>
 * 也就是说：synchronized锁无法再响应中断时释放锁
 * Created by liaoxudong
 * Date:2018/7/26
 */

public class SynchronizedInterrupt {

    public static void main(String[] args) throws Exception {
        syncInterruptTest();

//        lockInterruptTest();
    }

    /**
     * 测试lock.lockInterrupt()是否能在线程被中断后释放锁
     */
    private static void lockInterruptTest() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    if (Thread.interrupted()) {// 当前线程无法响应中断
                        System.out.println("线程被中断...");
                        break;
                    } else {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("线程继续运行");
                        Thread.yield();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }

    /**
     * 测试synchronized锁是否能在中断时释放锁，答案是不能
     *
     * @throws InterruptedException
     */
    private static void syncInterruptTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (SynchronizedInterrupt.class) {
                while (true) {
                    if (Thread.interrupted()) {// 当前线程无法响应中断
                        System.out.println("线程被中断...");
                        break;
                    } else {
                        System.out.println("线程继续运行");
                        Thread.yield();
                        /*try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            System.out.println("thread1休眠被中断..");
                            // ***********************
                            // 在响应中断后挂起当前线程然后唤醒其它线程达到释放锁的目的
                            *//*try {
                                SynchronizedInterrupt.class.wait();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            SynchronizedInterrupt.class.notifyAll();*//*
                        }*/
//                        System.out.println("thread1休眠中...");
                    }
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();// thread中断，看thread2是否会获取到锁执行，结果是不会，thread并未释放锁
    }
}
