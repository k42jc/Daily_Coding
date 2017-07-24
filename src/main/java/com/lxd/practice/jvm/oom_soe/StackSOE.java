package com.lxd.practice.jvm.oom_soe;

/**
 * 模拟实现栈溢出 stackOverFlowError
 *
 * VM args:-Xss128K
 * Created by liaoxudong on 2017/7/17.
 */
public class StackSOE {

    private static int stackLength = -1;

    public void addLength(){
        stackLength++;
        addLength();
    }

    public static void main(String[] args) throws Throwable {
        StackSOE stackSOE = new StackSOE();
        try {
            stackSOE.addLength();
        } catch (Throwable e) {
            System.out.println("stack length:"+stackLength);
            throw e;
        }
    }
}
