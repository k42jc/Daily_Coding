package com.lxd.practice.concurrent.netty.delimiter;

/**
 * Echo客户端与服务端交互示例
 * Created by liaoxudong on 2017/7/28.
 */
public class Test {

    public static void main(String[] args) {
        String sendMsg = "Hello world!";
        ClientMsg msg = new ClientMsg("127.0.0.1",8888,sendMsg);
        try {
            Object recvData = ClientFactory.sendMsg(msg, 5);
            System.out.println("服务端返回信息："+recvData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
