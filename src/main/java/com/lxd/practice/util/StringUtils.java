package com.lxd.practice.util;

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
}
