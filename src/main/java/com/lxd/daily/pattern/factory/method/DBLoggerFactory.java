package com.lxd.daily.pattern.factory.method;

/**
 * 数据库日志工厂
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class DBLoggerFactory extends LoggerFactory {

    @Override
    public Logger createLogger() {
        return new DBLogger();
    }
}
