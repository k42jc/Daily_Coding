package com.lxd.practice.arithmetic;

/**
 * Created by liaoxudong on 2017/8/17.
 */
public class StringDemo {

    public static int calcStrCharCount(Character target,String string) {
        char[] chars = string.toCharArray();
        int j =0;
        for(int i=0;i<chars.length;i++) {
            if (target.equals(chars[i])) {
                j++;
            }
        }
        return j;
    }

    static int position =0;
    public static void main(String[] args) {
        int i = calcStrCharCount('a', "abdsdaweqwa");
        System.out.println(i);

        int p = position;
        position += 2;
        System.out.println(p);
    }



}
