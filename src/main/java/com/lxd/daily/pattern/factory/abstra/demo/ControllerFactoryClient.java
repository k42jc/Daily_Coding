package com.lxd.daily.pattern.factory.abstra.demo;

/**
 * Created by liaoxudong
 * Date:2018/7/17
 */

public class ControllerFactoryClient {

    public static void main(String[] args) {
        ControllerFactory iosFactory = new IOSControllerFactory();
        iosFactory.createOperateController().operate();
        iosFactory.createFaceController().display();

        ControllerFactory andoridFactory = new AndoridControllerFactory();
        andoridFactory.createOperateController().operate();
        andoridFactory.createFaceController().display();
    }
}
