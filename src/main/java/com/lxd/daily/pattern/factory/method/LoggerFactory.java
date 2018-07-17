package com.lxd.daily.pattern.factory.method;

/**
 * 抽象工厂
 * Created by liaoxudong
 * Date:2018/7/16
 */

public abstract class LoggerFactory {

    abstract Logger createLogger();

    /**
     * 结合简单工厂模式
     * @return
     */
    public static LoggerFactory getInstance(Class<? extends LoggerFactory> clazz){
        if(clazz == null){
            throw new IllegalArgumentException("clazz");
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
