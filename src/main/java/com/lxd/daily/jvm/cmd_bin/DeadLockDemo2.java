package com.lxd.daily.jvm.cmd_bin;

/**
 * <h2>死锁演示2</h2>
 *
 * 本次演示线程1阻塞，线程2一直拿不到锁，并不是真正的死锁(监控工具不会检测到死锁，只会有thread1一直处于阻塞状态)
 * Created by liaoxudong on 2017/7/21.
 */
public class DeadLockDemo2 {

    private final static Object lock = new Object();

    public static void deadLock(){
        Thread thread1 = new Thread(){
          public void run(){
              synchronized (lock) {
                  while(true){

                  }
              }
          }
        };

        Thread thread2 = new Thread(){
            public void run(){
                try {
                    Thread.sleep(500);
                } catch(Exception e){

                }
                synchronized (lock){
                    System.out.println("获取到锁lock");
                }
            }
        };

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        deadLock();
    }
}
