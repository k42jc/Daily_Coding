package com.lxd.daily.util;

/**
 * 字符串工具类
 *
 * Created by liaoxudong on 2017/7/28.
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param string 待判断字符串
     * @return str == null || "" == str
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }

    /**
     * 计算某个字符在字符串中的位置
     * @param target
     * @param string
     * @return
     */
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
}
