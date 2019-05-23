package com.lxd.daily.interview.mayijinfu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池工厂
 */
public class ThreadPoolFactory {

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10,new NameThreadFactory());

    public static Future submit(Runnable runnable) {
        Future<?> submit = THREAD_POOL.submit(runnable);
        return submit;
    }

    /**
     * 自定义线程工厂，主要是自定义命名
     */
    static class NameThreadFactory implements ThreadFactory{
        private static final AtomicInteger atomicInt = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "cus-tl-" + atomicInt.getAndIncrement());
        }
    }

    public static void main(String[] args){
        ThreadPoolFactory.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+";xxxx");
            }
        });

        ThreadPoolFactory.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+";yyyy");
            }
        });
    }
}
