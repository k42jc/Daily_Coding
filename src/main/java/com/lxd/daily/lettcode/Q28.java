package com.lxd.daily.lettcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoxudong
 * Date:2018/6/14
 */

public class Q28 {

    public int strStr(String haystack, String needle) {
        if(needle == null || "".equals(needle)){
            return 0;
        }
        if(needle.length() > haystack.length()){
            return -1;
        }
        char[] chars = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        if(needles.length > chars.length){
            return -1;
        }
        // 创建数组用于保存所有可能的连续字符串
        List<String> strs = new ArrayList<>();
        for(int i=0;i<=chars.length-needles.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<needles.length;j++){
                sb.append(chars[j+i]);
            }
            strs.add(sb.toString());
        }
        // 判断是否存在匹配needle的字符串 如果匹配 当前下标与字符串所在下标是一致的
        for(int index=0;index<strs.size();index++){
            if(needle.equals(strs.get(index))){
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int i = new Q28().strStr("mississippi", "issip");
        System.out.println(i);
    }
}
