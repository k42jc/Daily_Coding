package com.lxd.daily.jvm.jmm_concurent;

/**
 *
 * volatile 变量自增运算测试
 * Created by liaoxudong on 2017/7/26.
 */
public class VolatileDemo {

    public static volatile int race = 0;

    public static void incre(){
        race++;
    }

    // 执行线程总数
    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i=0;i<THREADS_COUNT;i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<1000;j++) {
                        incre();
                    }
                }
            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
        /*while (Thread.activeCount() > 1) {
            Thread.yield();
        }*/

        Thread.sleep(1000);
        System.out.println(race);
    }
}
