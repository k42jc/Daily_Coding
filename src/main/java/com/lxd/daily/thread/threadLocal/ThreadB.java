package com.lxd.daily.thread.threadLocal;

/**
 *
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class ThreadB extends Thread{

    public ThreadB(String a) {
        super(a);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.get());
        ThreadLocalHolder.setValue("新值B");
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.get());
        ThreadLocalHolder.remove();
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.get());
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.getInheritable());
        ThreadLocalHolder.setInheritableValue("新值main");
        new ThreadA("A").start();
        new ThreadB("B").start();
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.getInheritable());


        new ThreadC("C").start();
    }
}
