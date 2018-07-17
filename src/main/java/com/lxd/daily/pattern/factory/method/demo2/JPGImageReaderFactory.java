package com.lxd.daily.pattern.factory.method.demo2;

/**
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class JPGImageReaderFactory implements ImageReaderFactory {

    @Override
    public ImageReader createImageReader() {
        return new JPGImageReader();
    }
}
