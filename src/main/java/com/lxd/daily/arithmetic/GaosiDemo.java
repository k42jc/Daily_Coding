package com.lxd.daily.arithmetic;

/**
 * 普通等差数列叠加与高斯算法效率差
 *
 * 输出如下：
 * <pre>
 *     sum=932356074711512064,耗时=42212
 *     sum2=932356074711512064,耗时2=0
 * </pre>
 *
 * 一个是O(n)的时间复杂度，一个是O(1)，差距明显
 * @author liaoxudong
 * @date 2019/5/23 23:12
 */
public class GaosiDemo {


    public static void main(String[] args){
        long start = 1;
        long end = 100000000000L;
        long sum = 0;
        long startTime = System.currentTimeMillis();
        for(long i=start;i<=end;i++) {
            sum = sum + i;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("sum=" + sum + ",耗时=" + (endTime - startTime));

        long startTime2 = System.currentTimeMillis();
        long sum2 = (start + end) * end / 2;
        long endTime2 = System.currentTimeMillis();
        System.out.println("sum2=" + sum2 + ",耗时2=" + (endTime2 - startTime2));

    }
}
