package com.lxd.daily.lettcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q5 {

    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        if(s.length() == 1){
            return s;
        }
        // 转为char数组
        char[] chars = s.toCharArray();
        // 反转字符串
        StringBuilder builder = new StringBuilder();
        for(int i=chars.length-1;i>=0;i--){
            builder.append(chars[i]);
        }
        String reverse = builder.toString();
        if (s.equals(reverse)) {// 翻转后还相等 肯定是了
            return reverse;
        }
        // FIXME 获取两个字符串最长的公共子串
        return "";
    }

    public static void main(String[] args){
        String babad = longestPalindrome("addb");
        System.out.println(babad);
    }
}
