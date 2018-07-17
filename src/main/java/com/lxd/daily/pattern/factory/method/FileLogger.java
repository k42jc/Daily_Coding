package com.lxd.daily.pattern.factory.method;

/**
 * 使用文件记录日志
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class FileLogger implements Logger{

    @Override
    public void log(String msg) {
        System.out.println("使用文件记录日志："+msg);
    }
}
