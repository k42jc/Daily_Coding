package com.lxd.daily.concurrent.future.future_demo;

/**
 * Created by liaoxudong on 2017/10/14.
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        Data<String> data = client.request();
        System.out.println("请求完毕");
        //可以去先做其它事情 balabala
        System.out.println("实际请求数据："+data.getResult());
    }
}
