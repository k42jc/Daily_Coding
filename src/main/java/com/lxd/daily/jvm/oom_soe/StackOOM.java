package com.lxd.daily.jvm.oom_soe;

/**
 * 模拟虚拟机栈的内存溢出情景
 *
 * Warning:因为java的线程会直接映射为操作系统的内核线程，执行本代码极容易造成系统死机，请注意！
 *
 * VM args:-Xss2M
 * Created by liaoxudong on 2017/7/17.
 */
public class StackOOM {

    private void dontStop(){
        while (true) {

        }
    }

    /**
     * 通过不断创建线程，每个线程占用一定的独立虚拟机栈区间
     * 线程数量越多，则容易出现内存溢出
     */
    public void stackLeakByThread(){
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {

        StackOOM stackOOM = new StackOOM();

        stackOOM.stackLeakByThread();

    }
}
