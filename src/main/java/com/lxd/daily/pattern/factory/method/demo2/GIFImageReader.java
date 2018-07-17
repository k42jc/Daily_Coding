package com.lxd.daily.pattern.factory.method.demo2;

/**
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class GIFImageReader implements ImageReader{

    @Override
    public void read() {
        System.out.println("Gif 图片读取器");
    }
}
