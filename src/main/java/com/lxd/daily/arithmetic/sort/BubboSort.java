package com.lxd.daily.arithmetic.sort;

import com.lxd.daily.util.JsonUtils;

/**
 * 冒泡排序
 * 原理：
 *      每次比较相邻两个元素，把最大的元素往后放，一次循环最少有一个元素完成预定排序
 * Created by liaoxudong
 * Date:2018/5/4
 */

public class BubboSort {

    /**
     * 冒泡排序
     * @param arrays
     */
    public static void sort(int[] arrays){
        if(arrays == null || arrays.length <= 0)
            throw new IllegalArgumentException("数组为空");
        for(int i=0;i<arrays.length-1;i++) {
            for(int j=i+1;j<arrays.length;j++) {// 每一轮将array[i]与array[i+1]~array[i-1]内所有数比较，如果某个数不符合排序则交换顺序
                int iValue = arrays[i];
                int jValue = arrays[j];
                if (iValue > jValue) {
                    arrays[j] = iValue;
                    arrays[i] = jValue;
                }

            }
            System.out.println(JsonUtils.toJson(arrays));
        }
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{2,1,15,23,31,82,62,12,43,809,66};
        sort(arrays);
        System.out.println(JsonUtils.toJson(arrays));
    }
}
