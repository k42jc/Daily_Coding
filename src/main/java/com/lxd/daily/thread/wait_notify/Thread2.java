package com.lxd.daily.thread.wait_notify;

/**
 * wait/notify机制下
 * wait使当前线程让出持有锁，进入WAITING状态
 * notify唤醒持有同一个锁的其它线程(如果有多个，则随机唤醒一个，其它线程如果不继续notify则一直维持WAITING状态)，从等待队列中退出进入可运行状态
 * notify不会立即释放对象锁，需要执行完自己的【同步块】内容后才会切换到被唤醒的线程继续执行
 * Created by liaoxudong
 * Date:2018/5/7
 */

public class Thread2 extends Thread{
    private final String lock;

    public Thread2(String name,String lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("notify 开始，"+System.currentTimeMillis());
            lock.notify();// notify后，当前同步块执行完成才会执行thread1
//            lock.notifyAll();//notifyAll会将所有等待当前锁的线程同时进入可运行状态，线程优先级高的先执行
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("notify 结束，"+System.currentTimeMillis());
        }
        // 非同步块的内容不会限制先执行完
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("非同步块内容");
        super.run();
    }

    public static void main(String[] args) {
        String lock = "1";
        new Thread1("thread1",lock).start();
        new Thread2("thread2",lock).start();

    }
}
