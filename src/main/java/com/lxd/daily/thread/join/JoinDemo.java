package com.lxd.daily.thread.join;

/**
 * join演示
 * join与sleep区别在于,join使用wait实现，会释放锁。sleep不释放锁
 * Created by liaoxudong
 * Date:2018/5/7
 */

public class JoinDemo extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"等待当前线程执行完毕...");
        try {
            Thread.sleep((int)(Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
    }

    public static void main(String[] args) {
        JoinDemo joinDemo = new JoinDemo();
        joinDemo.start();
        try {
            joinDemo.join();//让当前线程等待joinDemo执行完毕再继续执行，内部使用wait实现，如果目标线程isAlive则不断wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("等待Join执行完成后才会执行");
    }
}
