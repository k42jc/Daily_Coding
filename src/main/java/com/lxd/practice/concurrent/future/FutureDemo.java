package com.lxd.practice.concurrent.future;

import java.util.concurrent.*;

/**
 * 演示Future模式
 * Created by liaoxudong on 2017/10/14.
 */
public class FutureDemo {

    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        demo();
        System.exit(1);
    }

    public static void demo(){
        Future<String> future = executor.submit(new CallableImpl());
        try {
            String s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class CallableImpl implements Callable<String>{
        @Override
        public String call() throws Exception {
            StringBuilder builder = new StringBuilder("hello world!");
            for(int i=0;i<5;i++) {
                builder.append(" "+i);
                Thread.sleep(500);
            }
            return builder.toString();
        }
    }
}
