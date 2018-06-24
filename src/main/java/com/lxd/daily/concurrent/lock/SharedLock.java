package com.lxd.daily.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 支持对多两个线程获取同步状态的共享锁
 */
public class SharedLock implements Lock{
    private final Sync sync;

    public SharedLock(int currenLevel) {
        this.sync =new Sync(currenLevel);
    }
    private static class Sync extends AbstractQueuedSynchronizer{
        private int currentLevel;
        public Sync(int currentLevel){// 并发级别 最多同时支持几个线程获取锁
            if(currentLevel <= 0){
                throw new IllegalArgumentException("currentLevel");
            }
            this.currentLevel = currentLevel;
            setState(currentLevel);
        }

        @Override
        protected int tryAcquireShared(int count) {
            for(;;) {
                // 获取现有状态
                int state = getState();
                // 获取一次将状态减目标值 如果还大于等于0
                int target = state - count;
                if (target >= 0 && compareAndSetState(state, target)) {
                    return target;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int count) {
            for(;;){
                // 将状态恢复到原来
                int state = getState();
                int target = state + count;
                if (compareAndSetState(state, target)) {
                    return true;
                }
            }
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
