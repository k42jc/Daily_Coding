package com.lxd.daily.thread.wait_notify;

/**
 * Created by liaoxudong
 * Date:2018/5/7
 */

public class Thread1 extends Thread{
    private final String lock;

    public Thread1(String name, String lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("wait 开始,"+System.currentTimeMillis());
            try {
                lock.wait();//释放持有锁，进入等待队列，WAITING状态
                System.out.println("wait 结束,"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.run();
    }
}
