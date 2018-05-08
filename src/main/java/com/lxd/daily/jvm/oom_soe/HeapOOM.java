package com.lxd.daily.jvm.oom_soe;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码示例实现Java堆的内存溢出错误
 *
 * VM args:-verbose:gc -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * Created by liaoxudong on 2017/7/17.
 */
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
