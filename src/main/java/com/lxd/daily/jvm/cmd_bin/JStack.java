package com.lxd.daily.jvm.cmd_bin;

import java.util.Map;

/**
 * <h2>使用Thread.getAllStackTraces()获取与jstack命令获取类似信息</h2>
 * Created by liaoxudong on 2017/7/21.
 */
public class JStack {

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();
        for(Map.Entry<Thread, StackTraceElement[]> stackTrace:stackTraces.entrySet()){
            Thread thread = stackTrace.getKey();
            StackTraceElement[] stackTraceElements = stackTrace.getValue();
            System.out.println("线程："+thread.getId()+"::"+thread.getName());
            for (StackTraceElement element : stackTraceElements) {
                System.out.println("         "+element);
            }
        }
    }
}
