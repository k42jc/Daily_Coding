package com.lxd.practice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * <b>日期/时间工具类</b>
 * <br>
 * Created by liaoxudong on 2017/7/28.
 */
public class DateTimeUtils {

    private static final String DATE_TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化为：yyyy-MM-dd HH:mm:ss
     * @param date java.util.Date
     * @return 格式化之后的日期
     */
    public static String formatDatetime1(Date date){
        if(date == null)
            return "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_1);
        return simpleDateFormat.format(date);
    }

    /**
     * 日期格式化为：yyyy-MM-dd HH:mm:ss
     * @param timemills 时间毫秒数
     * @return 格式化之后的日期
     */
    public static String formatDatetime1(long timemills) {
        return formatDatetime1(new Date(timemills));
    }
}
