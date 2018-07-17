package com.lxd.daily.pattern.factory.method;

/**
 * 文件日志工厂
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class FileLoggerFactory extends LoggerFactory{

    @Override
    public Logger createLogger() {
        return new FileLogger();
    }
}
