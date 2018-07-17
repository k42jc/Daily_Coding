package com.lxd.daily.pattern.factory.method.demo2;

/**
 * GIF图片读取器工厂
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class GIFImageReaderFactory implements ImageReaderFactory {

    @Override
    public ImageReader createImageReader() {
        return new GIFImageReader();
    }
}
