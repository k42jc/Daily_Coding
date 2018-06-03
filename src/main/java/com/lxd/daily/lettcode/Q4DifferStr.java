package com.lxd.daily.lettcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串操作
 *
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。

 示例：

 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */
public class Q4DifferStr {

    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        // 转换为字符数组
        char[] chars = s.toCharArray();
        // 用于保存不重复子串的长度
        Set<Integer> nums = new HashSet<Integer>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<chars.length;i++){
            char c = chars[i];
            if(sb.toString().contains(c+"")){
                // 遇到重复保存上一次叠加最长的子串长度
                nums.add(sb.length());
                // 从重复位置截断并重新开始计算
                sb = new StringBuilder(sb.substring(sb.indexOf(c+"")+1));

            }
            sb.append(c);
        }
        // 字符串未出现重复字符的情况
        if(nums.size() == 0){
            return sb.length();
        }
        // 获取set中最大的数字
        int max = 0;
        for(Integer num:nums){
            if(num>max){
                max = num;
            }
        }
        // 同样 如果前面的子串的长度集中最大值还小于最后一次拼接子串的值，直接返回子串值
        return max<sb.length()?sb.length():max;
    }

    public static void main(String[] args){
        int abcabcbb = lengthOfLongestSubstring("abcabcbb");
        System.out.println(abcabcbb);
    }
}
