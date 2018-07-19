package com.lxd.daily.pattern.factory.abstra;

/**
 * Created by liaoxudong
 * Date:2018/7/17
 */

public class AbstractFactoryClient {

    public static void main(String[] args) {
        FaceFactory springFacroty = new SpringFaceFactory();
        springFacroty.createButton().display();
        springFacroty.createTextFiled().display();
        springFacroty.createComboBox().display();

        FaceFactory summerFactory = new SummerFaceFactory();
        summerFactory.createButton().display();
        summerFactory.createTextFiled().display();
        summerFactory.createComboBox().display();
    }
}
