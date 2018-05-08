package com.lxd.daily.jvm.cmd_bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h2>演示线程死循环、线程等待的状态监控，使用jconsole的线程页签查看</h2>
 * Created by liaoxudong on 2017/7/21.
 */
public class ThreadStatDemo {

    /**
     * 线程死循环演示
     */
    public static void createBusyThread(){
        Thread thread = new Thread("testBusiThread"){
            public void run(){
                while(true);
            }
        };
        thread.start();
    }

    public static void createWaitingThread(final Object lock){
        Thread thread = new Thread("testWaitingThread"){
            public void run(){
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();

        createBusyThread();

        bufferedReader.readLine();
        createWaitingThread(new Object());
    }
}

