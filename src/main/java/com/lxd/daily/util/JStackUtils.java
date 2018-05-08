package com.lxd.daily.util;

import java.util.Map;

/**
 * 获取线程堆栈信息
 * 与jstack pid效果一致
 * Created by liaoxudong
 * Date:2018/5/8
 */

public final class JStackUtils {

    /**
     * 获取线程快照信息
     *
     * @return
     */
    public static String getJavaStackTrace() {
        StringBuffer msg = new StringBuffer();
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
            Thread thread = (Thread) stackTrace.getKey();
            StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
            if (thread.equals(Thread.currentThread())) {
                continue;
            }
            msg.append("\n 线程:").append(thread.getName()).append("\n");
            for (StackTraceElement element : stack) {
                msg.append("\t").append(element).append("\n");
            }
        }
        return msg.toString();
    }
}
