package com.lxd.daily.pattern.flyweight;

/**
 * 享元模式客户端调用示例
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class FlyweightClient {

    public static void main(String[] args) {
        Media text = MediaFactory.getInstance().getMedia("text");
        // 允许运行时传入属性
        text.show(new Property("D:/xxx/",30));
        Media text1 = MediaFactory.getInstance().getMedia("text");
        text.show(new Property("D:/xxx/",40));
        Media text2 = MediaFactory.getInstance().getMedia("text");
        text.show(new Property("D:/xxx/",50));
        Media text3 = MediaFactory.getInstance().getMedia("text");
        text.show(new Property("D:/xxx/",60));

        System.out.println(((text == text1) == true) == (text2 == text3));

        Media video = MediaFactory.getInstance().getMedia("video");
        video.show(new Property("D:/", 320));
        Media video1 = MediaFactory.getInstance().getMedia("video");
        video1.show(new Property("E:/", 120));

        System.out.println(video == video1);


    }
}
