package com.lxd.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liaoxudong on 2017/10/9.
 */
public class App2 {
    private static volatile boolean isRunning = true;

    public static void main(String[] args) {
        /*Runnable runnable = new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        };

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
//                System.exit(1);
                System.out.println(111);
            }
        });

        for (int i=0;i<10;i++) {
            new Thread(runnable).start();
        }*/
        /*int i = 0;
        System.out.println(i);
        int a = i++;
        System.out.println(a);
        System.out.println(i);
        int b = ++a;
        System.out.println(b);
        System.out.println(a);
        int c=a+b;
        System.out.println(a);
        System.out.println(b);
        System.out.println(i);
        System.out.println(c);*/
        /*synchronized (App2.class) {
            while (isRunning){
                try {
                    App2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/


demo();


    }

    private static Lock lock = new ReentrantLock();
    private static Condition cond1 = lock.newCondition();
    private static Condition cond2 = lock.newCondition();
    private static Condition cond3 = lock.newCondition();
    private static Condition cond4 = lock.newCondition();

    static volatile int target = 4;
    public static void demo() {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                lock.lock();
                try {
                    for(int i=0;i<10;i++) {
                        while (target != 1) {
                            cond1.await();
                        }
                        System.out.print("1");
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
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                lock.lock();
                try {
                    for(int i=0;i<10;i++) {
                        while (target != 2) {
                            cond2.await();
                        }
                        System.out.print("2");
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
        Thread thread3 = new Thread(){
            @Override
            public void run() {
                lock.lock();
                try {
                    for(int i=0;i<10;i++) {
                        while (target != 3) {
                            cond3.await();
                        }
                        System.out.print("3");
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
        Thread thread4 = new Thread(){
            @Override
            public void run() {
                lock.lock();
                try {
                    for(int i=0;i<10;i++) {
                        while (target != 4) {
                            cond4.await();
                        }
                        System.out.print("4");
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
