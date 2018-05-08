package com.lxd.daily.thread.wait_notify.printStar;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 使用wait/notifyAll机制交替打印星星
 * 本示例是在main线程中完成
 * Created by liaoxudong
 * Date:2018/5/7
 */

public class PrintStar {

    private volatile boolean printOther = false;

    public synchronized void printStar1(){
        if (printOther == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("☆");
        printOther = true;
        notifyAll();
    }

    public synchronized void printStar2(){
        if (printOther == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("△");
        printOther = false;
        notifyAll();
    }

    public static void main(String[] args) {
        PrintStar printStar = new PrintStar();
        for(int i=0;i<20;i++) {
            printStar.printStar1();
            printStar.printStar2();
        }

        // 显示jvm所有线程信息
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] allThreadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(allThreadIds);
        for (ThreadInfo info : threadInfos) {
            System.out.println(info.getThreadName()+"-"+info.getThreadState());
        }
    }
}
