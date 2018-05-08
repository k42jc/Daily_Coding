package com.lxd.daily.jvm.class_load;

/**
 * <h2>演示初始化过程的同步</h2>
 * Created by liaoxudong on 2017/7/20.
 */
public class DeadLoopClass {

    static{
        if(true){
            System.out.println(Thread.currentThread()+" init DeadLoopClass");
            while (true) {

            }
        }
    }



}

/**
 * 运行下面方法输出示例如下：
 * <pre>
 *     Thread[Thread-1,5,main] start!
 *     Thread[Thread-1,5,main] init DeadLoopClass
 *     Thread[Thread-0,5,main] start!
 * </pre>
 *
 * 说明在类初始化过程中只有一个线程会执行类的&lt;clinit&gt;() --> 由编译器自动收集类中的所有类变量的赋值动作和静态语句块中的语句而产生的
 * 如果当前线程未执行完成，则阻塞
 */
class Test{
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start!");
                DeadLoopClass deadLoopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}
