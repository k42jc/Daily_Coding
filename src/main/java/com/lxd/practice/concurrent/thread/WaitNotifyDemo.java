package com.lxd.practice.concurrent.thread;

/**
 * 演示wait/notify通知机制
 * Created by liaoxudong on 2017/10/14.
 */
public class WaitNotifyDemo {
        private static Object lock = new Object();
        private static boolean oneRunning = false;

    public static void main(String[] args) {
        demo();
    }

    /**
     * 演示wait/notify的等待唤醒操作
     * 根据oneRunning是否为true控制输出顺序
     */
        public static void demo(){
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    synchronized (lock) {
                        for(int i=0;i<10;i++) {
                            while (!oneRunning) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("☆");
                            oneRunning = false;
                            lock.notifyAll();
                        }
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    synchronized (lock) {
                        for(int i=0;i<10;i++) {
                            while (oneRunning) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("★");
                            oneRunning = true;
                            lock.notifyAll();
                        }
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
}
