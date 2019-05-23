package com.lxd.daily.interview.mayijinfu;

/**
 * 字符串工具类
 */
public final class StringUtils {

    /**
     * 判断是否为空串
     * @param param 字符串参数
     * @return
     */
    public static boolean isEmpty(String param) {
        return param == null || "".equals(param);
    }
}
