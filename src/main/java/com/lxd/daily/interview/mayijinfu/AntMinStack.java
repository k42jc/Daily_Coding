package com.lxd.daily.interview.mayijinfu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 设计含最小函数min()、pop()、push()的栈AntMinStack
 要求：
 1.AntMinStack实现测试，满足栈特性
 2.要求min、push、pop、的时间复杂度都是O(1)
 */
public class AntMinStack<T extends Comparable> {
    // 通过数组实现栈
    private Object[] arrays = new Object[10];
    // 保存最小值引用
    private T min;
    // 保存栈中元素个数
    private int size;

    public T min(){
        if(size <= 0){
            throw new IllegalStateException("stack is empty");
        }
        if(min == null){// 最小值已经被弹出来了，则需要重新计算最小值
            Object[] dists = new Object[size];
            System.arraycopy(arrays,0,dists,0,size);
            Arrays.sort(dists);
            min = (T)dists[0];
        }
        return min;
    }

    /**
     * 压栈
     * @param t
     * @return
     */
    public T push(T t) {
        if (size >= arrays.length) {
            Object[] dists = new Object[arrays.length*2];
            // 执行扩容
            System.arraycopy(arrays,0,dists,0,size);
            arrays = dists;
        }
        arrays[size++] = t;
        // 比较min与目标元素，如果t小于min则将min=t
        if(min == null || min.compareTo(t) > 0){
            min = t;
        }
        return t;
    }

    /**
     * 弹栈
     * @return
     */
    public T pop(){
        if(size <= 0){
            throw new IllegalStateException("stack is empty");
        }
        int index = --size;
        Object result = arrays[index];
        arrays[index] = null;
        if (min.equals(result)) {// 最小的元素被弹出去，则需要重新计算最小值
            min = null;
        }
        return (T)result;
    }


    /**
     * 最好的情况下(目标数组不经历扩容、排序)时间复杂度为O(1)
     * @param args
     */
    public static void main(String[] args){
        AntMinStack<Comparable> stack = new AntMinStack<>();
        // 随机数入栈,如果为偶数，弹栈
        for(int i=0;i<1000000;i++) {
            int nextInt = new Random().nextInt(100000);
            stack.push(nextInt);
            System.out.println(stack.min());
            if (nextInt %2 == 0) {
                System.out.println(stack.pop());
                System.out.println(stack.min());
            }
        }
/*
        stack.push(10);
        stack.push(4);
        stack.push(2);
        stack.push(8);
        stack.push(102);
        stack.push(-1);

        System.out.println(stack.min());
        System.out.println(stack.pop());
        System.out.println(stack.min());
        stack.push(1);
        System.out.println(stack.min());
        System.out.println(stack.pop());
        System.out.println(stack.min());*/
    }
}
