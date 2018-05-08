package com.lxd.daily.concurrent.thread_pool;

import java.lang.management.ManagementFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * 线程池大小计算的简单实现示例
 * <br>
 * Created by liaoxudong on 2017/7/27.
 */
public class SimplePoolSizeCaculator extends PoolSizeCaculator{

    private Runnable task;

    public SimplePoolSizeCaculator(Runnable task) {
        this.task = task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    @Override
    protected Runnable creatTask() {
        return task;
    }

    @Override
    protected BlockingQueue createWorkQueue() {
        return new LinkedBlockingDeque(SAMPLE_QUEUE_SIZE);
    }

    @Override
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }


}
