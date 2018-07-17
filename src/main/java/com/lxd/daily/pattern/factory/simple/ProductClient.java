package com.lxd.daily.pattern.factory.simple;

/**
 * 简单工厂客户端调用示例
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class ProductClient {

    public static void main(String[] args) {
        Product product = Product.getInstance(HistoProduct.class);
        product.display();

        Product pieProduct = Product.getInstance(PieProduct.class);
        pieProduct.display();


        Product lineProduct = Product.getInstance(LineProduct.class);
        lineProduct.display();

        // 使用目标类全限定名来指定初始化，实际使用中可以将字符抽取到配置文件中
        // 基于里氏替换、与依赖倒转原则的设计、符合程序设计的开放-封闭原则
        Product instance = Product.getInstance("com.lxd.daily.pattern.factory.simple.PieProduct");
        instance.display();
    }
}
