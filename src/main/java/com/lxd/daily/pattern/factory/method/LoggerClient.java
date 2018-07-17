package com.lxd.daily.pattern.factory.method;

/**
 * 工厂方法模式相对于简单工厂，引入了顶级抽象工厂概念
 *
 * 具体子工厂只需要负责对应子类创建即可
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class LoggerClient {

    public static void main(String[] args) {
        // 工厂方法 客户端控制具体工厂子类的初始化过程
        LoggerFactory factory = null;
        factory = new FileLoggerFactory();
        Logger logger = factory.createLogger();
        logger.log("xxx");
        factory = new DBLoggerFactory();
        Logger dbLogger = factory.createLogger();
        dbLogger.log("xxx");

        // 工厂方法+简单工厂结合，将具体工厂的初始化过程封装在抽象工厂中，客户端只需要执行获取实例命令即可
        LoggerFactory loggerFactory = LoggerFactory.getInstance(FileLoggerFactory.class);
        Logger fileLogger = loggerFactory.createLogger();
        fileLogger.log("yyy");

        loggerFactory = LoggerFactory.getInstance(DBLoggerFactory.class);
        Logger dbLogger2 = loggerFactory.createLogger();
        dbLogger2.log("yyy");
    }
}
