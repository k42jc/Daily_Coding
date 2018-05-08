package com.lxd.daily.cas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 引用计数器示例
 * Created by liaoxudong on 2017/8/21.
 */
public class ReferenceCounterDemo {
    private static final Logger logger = LoggerFactory.getLogger(ReferenceCounterDemo.class);
    // 获取cas更新器，使用当前对象的refCnt作为对照标准
    private AtomicIntegerFieldUpdater cntUpdater = AtomicIntegerFieldUpdater.newUpdater(ReferenceCounterDemo.class, "refCnt");
    // volatile修饰 用于获取内存最新值
    private volatile int refCnt = 1;

    public int getRefCnt() {
        return refCnt;
    }

    public final void setRefCnt(int refCnt) {
        this.refCnt = refCnt;
    }

    public void retain(){
        retain0(1);
    }

    public void retain(int increment) {
        if (increment <= 0) {
            throw new IllegalArgumentException("必须为正数");
        }
        retain0(increment);
    }

    /**
     * 引用计数器增加1
     * @param increment
     */
    private void retain0(int increment) {
        for(;;) {
            int refCnt = this.refCnt;
            final int newRefCnt = refCnt + increment;
            // 此时refCnt = 0 表示被异常引用 直接退出
            if (newRefCnt <= increment) {
                throw new IllegalArgumentException("当前计数器引用错误，退出！");
            }
            // refCnt引用+increment
            if (cntUpdater.compareAndSet(this, refCnt, newRefCnt)) {
                break;
            }
        }
    }

    public boolean release(){
        return release0(1);
    }

    /**
     * 引用释放一次
     * @param decrement
     * @return
     */
    private boolean release0(int decrement) {
        for(;;) {
            int refCnt = this.refCnt;
            if (refCnt <= decrement) {
                throw new IllegalArgumentException("参数错误！");
            }
            if (cntUpdater.compareAndSet(this, refCnt, refCnt - decrement)) {
                if (this.refCnt == 1) {
                    logger.info("内存可以释放！");
                    return true;
                }
                return false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReferenceCounterDemo demo = new ReferenceCounterDemo();
        //测试多线程环境下的计数器效果
        Runnable inc = new Runnable() {
            @Override
            public void run() {
                demo.retain();
            }
        };
        Runnable dec = new Runnable() {
            @Override
            public void run() {
                demo.release();
            }
        };

        for (int i=0;i<10;i++) {
            new Thread(inc).start();
        }
//        Thread.sleep(5000);
        for (int i=0;i<9;i++) {
            new Thread(dec).start();
        }
    }


}
