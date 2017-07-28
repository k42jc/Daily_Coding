package com.lxd.practice.concurrent.thread_pool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 自定义异步IO任务
 * Created by liaoxudong on 2017/7/27.
 */
public class AsyncIOTask implements Runnable{
//    private byte[] _1M = new byte[1024*1024];

    @Override
    public void run() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String URL = "https://www.baidu.com";
        try {
            URL url = new URL(URL);
            URLConnection urlConnection = url.openConnection();
            connection = (HttpURLConnection)urlConnection;
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
    }
}
