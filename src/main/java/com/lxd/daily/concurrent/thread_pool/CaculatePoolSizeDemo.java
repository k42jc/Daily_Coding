package com.lxd.daily.concurrent.thread_pool;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 模拟计算线程池大小示例
 * Created by liaoxudong on 2017/7/27.
 */
public class CaculatePoolSizeDemo {

    public static void main(String[] args) {
        SimplePoolSizeCaculator simplePoolSizeCaculator = new SimplePoolSizeCaculator(new AsyncIOTask());
        simplePoolSizeCaculator.calculateBoundaries(new BigDecimal(1.0),new BigDecimal(1024*1000));

        new ThreadPoolExecutor(16,16,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(42667));
        Executors.newFixedThreadPool(16);
    }
}
