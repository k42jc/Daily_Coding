package com.lxd.daily.lettcode;

import com.lxd.daily.util.JsonUtils;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。

 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

 示例:

 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]
 */
public class Q1TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length <= 0){
            throw new IllegalArgumentException("nums");
        }
        int[] results = new int[2];
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target){
                    results[0] = i;
                    results[1] = j;
                    return results;
                }
            }
        }
        return results;
    }

    public static void main(String[] args){
        int[] arrays = new int[]{1,8,9,11};
        int[] ints = twoSum(arrays, 9);
        System.out.println(JsonUtils.toJson(ints));
        String r = "123";
        String substring = r.substring(2, 3);
        System.out.println(substring);
    }
}
