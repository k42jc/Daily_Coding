package com.lxd.daily.concurrent.thread_pool;

/**
 * 线程池接口
 * Created by liaoxudong on 2017/9/4.
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 将任务添加到线程池队列等待执行
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     * @param timemills
     * @return
     */
    boolean shutdown(long timemills);

    /**
     * 线程池大小
     * @return
     */
    int size();
}
