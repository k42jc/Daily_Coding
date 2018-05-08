package com.lxd.daily.thread.threadLocal;

/**
 * Created by liaoxudong
 * Date:2018/5/8
 */

public class ThreadC extends Thread{

    public ThreadC(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.getInheritable());
        ThreadLocalHolder.setInheritableValue("新值C");
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.getInheritable());
        ThreadLocalHolder.removeInheritable();
        System.out.println(Thread.currentThread().getName()+ThreadLocalHolder.getInheritable());
    }
}
