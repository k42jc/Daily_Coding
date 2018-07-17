package com.lxd.daily.pattern.factory.method.demo2;

/**
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class ImageReaderClient {
        static byte bb;
        static char ll;
        static int xx;
        static short cc;
        static float vv;


    public static void main(String[] args) {
        ImageReaderFactory factory = null;
        factory = new JPGImageReaderFactory();
        ImageReader jpgReader = factory.createImageReader();
        jpgReader.read();
        factory = new GIFImageReaderFactory();
        ImageReader gifReader = factory.createImageReader();
        gifReader.read();
        Integer i = 1;
        int x = i;
        byte y = 127;
        System.out.println(y);
        char j = '联';
        char m = '通';
        char result = '中'+'国';
        System.out.println(result);
        System.out.println('中');
        System.out.println('国');
        System.out.println('联');
        System.out.println('通');
        System.out.println(vv);
        vv = y;
        int vv = 2^2;
        System.out.println(vv);
    }
}
