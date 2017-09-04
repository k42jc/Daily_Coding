package com.lxd.practice.concurrent.thread_pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池的默认实现
 * 提供线程池默认线程数量配置、任务线程阻塞队列
 * 任务线程加入等待FIFO队列，执行线程从队列内获取线程执行
 * Created by liaoxudong on 2017/9/4.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{
    private static final int DEFAULT_THREAD_NUMS = Runtime.getRuntime().availableProcessors() +1;
    private int threads;
    // 任务队列，放入线程池执行的任务先加入队列
    private final LinkedList<Job> jobQuene = new LinkedList<Job>();
    // 执行任务的工作线程列表，存放线程池内的线程
    private final List<Worker> workerList = Collections.synchronizedList(new ArrayList<>());
    private AtomicLong threadsNs = new AtomicLong();

    public DefaultThreadPool(){
        this.threads = DEFAULT_THREAD_NUMS;
        init();
    }

    public DefaultThreadPool(int threads) {
        if (threads <= 0) {
            throw new IllegalArgumentException("threads");
        }
        this.threads = threads;
        init();
    }

    private void init() {
        for(int i=0;i<threads;i++) {
            Worker worker = new Worker();
            workerList.add(worker);
            new Thread(worker, "ThreadPool-Worker-" + threadsNs.incrementAndGet()).start();

        }
    }

    @Override
    public void execute(Job job) {
        if(job == null) throw new IllegalArgumentException("job");
        synchronized (jobQuene) {
            jobQuene.addLast(job);
            jobQuene.notify();
        }
    }

    @Override
    public boolean shutdown(long timemills) {
        if (timemills > 0) {
            try {
                Thread.sleep(timemills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            // 即关闭线程池中的工作线程
            for (Worker worker : workerList) {
                worker.shutdown();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int size() {
        return workerList.size();
    }

    class Worker implements Runnable{
        private volatile boolean running = true;
        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobQuene) {
                    while(jobQuene.isEmpty()){//任务队列中无任务时，一直等待
                        try {
                            jobQuene.wait();
                        } catch (InterruptedException e) {
                            // 感知到外部对WorkerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobQuene.pop();
                }
                if (job != null) {
                    try {
                        // 执行任务，而不是启动任务线程
                        job.run();
                    } catch (Exception e) {
                        // 忽略任务异常
                    }
                }
            }
        }

        void shutdown(){
            this.running = false;
        }
    }

}
