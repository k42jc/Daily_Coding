package com.lxd.daily.concurrent.thread_local;

import java.util.Random;

/**
 * 对比threadLocal与资源竞争的效率
 *
 * 同样启动10个线程，执行1亿次随机数生成
 *
 * 输出如下：
 * 10线程竞争同一个random执行1亿次时间：164256
 * 10线程使用threadLocal random执行1亿次时间：1883
 *
 * 第一个方法10个线程不断竞争同一个random，因为Random是线程安全的，每个线程轮流使用可能还会频繁的发生竞争，导致效率低下
 * 第二个方法10个线程各自拥有一个random，避免了线程间的资源竞争问题
 * @author liaoxudong
 * @date 2019/5/23 22:36
 */
public class Demo1 {

    private static final Random random = new Random(100);


    private static final ThreadLocal<Random> threadLocalRandom = ThreadLocal.withInitial(() -> new Random(100));


    static class CurrentRandom implements Runnable{

        private int mode;

        public CurrentRandom(int mode) {
            this.mode = mode;
        }

        @Override
        public void run() {
            for(int i=0;i<100000000;i++) {
                if (mode == 0) {
                    random.nextInt();
                } else {
                    threadLocalRandom.get().nextInt();
                }
            }

        }
    }


    public static void main(String[] args){
        long mutexTime = testMutexRandom(0);
        long threadLocalTime = testMutexRandom(1);


        System.out.println("10线程竞争同一个random执行1000w次时间：" + mutexTime);
        System.out.println("10线程使用threadLocal random执行1000w次时间：" + threadLocalTime);
    }

    /**
     *
     * @param mode mode=0表示所有线程竞争同一个random，其它数字表示使用ThreadLocal为每个线程生成一个random
     * @return
     */
    private static long testMutexRandom(int mode) {
        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];

        long start = System.currentTimeMillis();
        for(int i=0;i<threadCount;i++) {
            threads[i] = new Thread(new CurrentRandom(mode));
            threads[i].start();
        }

        for(int i=0;i<threadCount;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        return end-start;
    }
}
