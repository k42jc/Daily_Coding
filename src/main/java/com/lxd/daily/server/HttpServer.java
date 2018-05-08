package com.lxd.daily.server;

/**
 * HTTP 服务器接口
 * Created by liaoxudong on 2017/9/4.
 */
public interface HttpServer {

    /**
     * 绑定端口
     * @param port 端口
     */
    void bindPort(int port);

    /**
     * 启动服务器
     */
    void bootstrap();

    /**
     * 设置web服务所在路径
     * @param path
     */
    void setBasePath(String path);
}
