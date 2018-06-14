package com.lxd.daily.lettcode;

/**
 * Created by liaoxudong
 * Date:2018/6/13
 */

public class Q258 {

    public int addDigits(int num) {
        if(num < 0){
            throw new IllegalArgumentException("num为非负整数");
        }
        String numStr = String.valueOf(num);
        if(numStr.length() == 1){
            return Integer.parseInt(numStr);
        }else{
            // 分割数字相加
            char[] numChars = numStr.toCharArray();
            int result = 0;
            for(char numChar:numChars){
                result += Integer.valueOf(numChar+"");
            }
            if(String.valueOf(result).length() > 1){
                result = addDigits(result);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int i = new Q258().addDigits(38);
        System.out.println(i);
    }
}
