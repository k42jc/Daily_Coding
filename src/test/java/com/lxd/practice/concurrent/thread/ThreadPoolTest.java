package com.lxd.practice.concurrent.thread;

import com.lxd.practice.concurrent.thread_pool.DefaultThreadPool;
import com.lxd.practice.concurrent.thread_pool.ThreadPool;
import org.junit.Test;

/**
 * Created by liaoxudong on 2017/9/4.
 */
public class ThreadPoolTest {

    @Test
    public void executeTest(){
        ThreadPool<Runnable> threadPool = new DefaultThreadPool<>();
        threadPool.execute(new Job());
        System.out.println(threadPool.size());
    }

    class Job implements Runnable{
        @Override
        public void run() {
            System.out.println("线程池执行-"+Thread.currentThread().getName());
        }
    }
}
