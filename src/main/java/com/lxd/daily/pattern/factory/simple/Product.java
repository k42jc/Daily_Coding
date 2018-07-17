package com.lxd.daily.pattern.factory.simple;

/**
 * Created by liaoxudong
 * Date:2018/7/16
 */

public abstract class Product {

    abstract void display();

    /**
     * 客户端指定将要初始化的子类class对象
     * @param product
     * @return
     */
    public static Product getInstance(Class<? extends Product> product){
        try {
            return product.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 可以在客户端的配置文件中指定需要获取的子类全限定名
     * @param className 类全限定名
     * @return
     */
    public static Product getInstance(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return (Product)aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
