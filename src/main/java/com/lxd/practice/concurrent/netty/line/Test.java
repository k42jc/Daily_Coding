package com.lxd.practice.concurrent.netty.line;

/**
 * Created by liaoxudong on 2017/7/28.
 */
public class Test {

    public static void main(String[] args) {
//        TimeServer.bootstrap(8888);

        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        TimeClientHandler handler = new TimeClientHandler();
        handler.setMsg("QUERY TIME"+System.getProperty("line.separator"));
        TimeClient timeClient = new TimeClient(handler);
        timeClient.connect("127.0.0.1",8888);
    }
}
