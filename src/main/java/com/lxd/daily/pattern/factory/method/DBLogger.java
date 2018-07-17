package com.lxd.daily.pattern.factory.method;

/**
 * 数据库记录日志
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class DBLogger implements Logger {

    @Override
    public void log(String msg) {
        System.out.println("使用数据库记录日志："+msg);
    }
}
