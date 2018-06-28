package com.lxd.daily.lettcode;

import java.util.*;

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
       /* String babad = longestPalindrome("addb");
        System.out.println(babad);*/
        /*int i = nMuti(4);
        System.out.println(i);
        LinkedList<Integer> list = new LinkedList<>();*/
        int i1 = (int)(Math.pow(2, 31)-2);
        double i2 = Math.pow(2, 32);
        int maxValue = Integer.MAX_VALUE;
        System.out.println(i2 > maxValue);

        System.out.println(i1);

        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {
        // 12 0000 1100
        //         0011
        //
        // 21 0001 0101
        if(x == 0 || x == Integer.MIN_VALUE){
            return 0;
        }
        boolean negative = false;
        if(x < 0){
            negative = true;
        }
        String str = Math.abs(x)+"";
        int size = str.length();
        String[] nums = new String[size];
        for(int index=0;index<size;index++){
            nums[index] = str.substring(index,index+1);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=size-1;i>=0;i--){
            sb.append(nums[i]);
        }
        double dValue = Double.valueOf(sb.toString());
        if(dValue > Integer.MAX_VALUE){
            return 0;
        }
        int result = Integer.parseInt(sb.toString());
        return negative?result*-1:result;
    }


    private static int nMuti(int n) {
        if (n <= 1) {
            return n;
        }
        return n * nMuti(n - 1);
    }

}
