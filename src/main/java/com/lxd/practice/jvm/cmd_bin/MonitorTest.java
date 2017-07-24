package com.lxd.practice.jvm.cmd_bin;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>演示虚拟机内存变化情况</h2>
 * <br>
 * <p>
 *      以64kb/50毫秒的频率往Java堆中填充数据，一共填充1000次，然后使用JConsole的内存页进行监视
 * </p>
 * Created by liaoxudong on 2017/7/21.
 */
public class MonitorTest {

    static class OOMObject{
        private byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) {
        List list = new ArrayList<OOMObject>();

        for(int i=0;i<num;i++) {
            // 稍作延迟，方便监控
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list.add(new OOMObject());
        }
        //主动发起Full GC，结合延迟令监控曲线更明显
        System.gc();
            while (true) {

            }
    }

    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fillHeap(1000);
    }
}
