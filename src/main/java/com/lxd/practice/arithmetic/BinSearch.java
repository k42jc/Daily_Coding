package com.lxd.practice.arithmetic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 二分查找演示
 * Created by liaoxudong on 2017/8/22.
 */
public class BinSearch {
    private static final Logger logger = LoggerFactory.getLogger(BinSearch.class);
    /**
     * 二分查找/折半查找/Arrays.binarySearch(...)
     * @param arrays
     * @param target
     * @return 指定元素下标
     */
    public static int search(int[] arrays, int target) {
        int count = 0;
        if (arrays == null || arrays.length <= 1) {// 数组元素小于1个就不找了
            return -1;
        }
        // 先排序
        Arrays.sort(arrays);
        int min = 0;
        int max = arrays.length -1;
        int mid;
        while (min <= max) {
            count += 1;
            mid = (min+max)/2;
            if (arrays[mid] == target) {
                logger.info("查找次数：" + count);
                return mid;
            }else if(arrays[mid] > target){
                max = mid-1;
            }else{
                min = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arrays = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(search(arrays, 1));
    }
}
