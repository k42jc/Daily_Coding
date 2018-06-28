package com.lxd.daily.concurrent.lock;

public class TestDemo {

    public static void main(String[] args){
        SharedLock sharedLock = new SharedLock(2);
        Runnable worker = new Runnable(){
            @Override
            public void run() {
                while (true) {
                    sharedLock.lock();
                    try {
                        Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        sharedLock.unlock();
                    }
                }

            }
        };
        for(int i=0;i<10;i++) {
            new Thread(worker,"thread:"+i).start();
        }

        for (int i=0;i<10;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
