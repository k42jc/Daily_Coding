package com.lxd.daily.lettcode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.IntStream;


/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。

 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。

 示例 1:

 nums1 = [1, 3]
 nums2 = [2]

 中位数是 2.0
 示例 2:

 nums1 = [1, 2]
 nums2 = [3, 4]

 中位数是 (2 + 3)/2 = 2.5
 */
public class Q3MiddleNum {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 == null){
            throw new IllegalArgumentException("nums1 & nums2 all null");
        }
        //合并数组
        int[] dests = new int[nums1.length+nums2.length];
        System.arraycopy(nums1,0,dests,0,nums1.length);
        System.arraycopy(nums2,0,dests,nums1.length,nums2.length);
        // 排序
        Arrays.sort(dests);
        // 找中位数
        int middleNum = dests.length/2;
        if(dests.length % 2 != 0){// 合并后数组元素为单数个，直接找到中位数
            return dests[middleNum];
        }else{
            return new BigDecimal(dests[middleNum-1]+dests[middleNum]).divide(new BigDecimal(2)).doubleValue();
        }

    }

    public static void main(String[] args){
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        IntStream chars = "sssss".chars();
        char[] chars1 = "asdasdasd".toCharArray();
        System.out.println(medianSortedArrays);
    }
}
