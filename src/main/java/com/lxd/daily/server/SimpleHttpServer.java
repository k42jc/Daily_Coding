package com.lxd.daily.server;

import com.lxd.daily.concurrent.thread_pool.DefaultThreadPool;
import com.lxd.daily.concurrent.thread_pool.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单http服务器
 * 服务端基于线程池，避免每个客户端连接创建一个线程
 *
 * 启动服务器，监听端口，接收客户端请求并放入线程池处理
 * Created by liaoxudong on 2017/9/4.
 */
public class SimpleHttpServer implements HttpServer{

    private static final Logger logger = LoggerFactory.getLogger(SimpleHttpServer.class);
    private int port;
    private final ThreadPool<HttpRequestHandler> pool = new DefaultThreadPool<>(1);
    private String basePath;
    private static final String CLASS_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    public SimpleHttpServer(int port) {
        this.port = port;
        this.basePath = CLASS_PATH;
    }
    public SimpleHttpServer(int port,String basePath) {
        this.port = port;
        this.basePath = basePath;
    }

    @Override
    public void bindPort(int port) {
        this.port = port;
    }

    @Override
    public void bootstrap() {
        try {
            // 以port端口启动服务器 接收客户端请求
            ServerSocket serverSocket = new ServerSocket(port);
            logger.info("启动完成，端口：【{}】,basePath：【{}】",port,basePath);
            Socket socket = null;
            while ((socket =serverSocket.accept()) != null) {
//                logger.info("收到客户端请求："+socket);
                pool.execute(new HttpRequestHandler(socket));
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setBasePath(String path) {
        // 如果设置的路径有效，则设置。否则设置为当前类所在路径
        if(path != null && new File(path).exists() && new File(path).isDirectory())
            this.basePath = path;
        else
            this.basePath = CLASS_PATH;
    }

    class HttpRequestHandler implements Runnable{
        private Socket socket;
        HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            logger.debug("Current Thread:"+Thread.currentThread().getName());
            BufferedReader reader = null;
            PrintWriter writer = null;
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 由请求的相对路径计算出文件所在绝对路径
                String filePath = basePath + header.split(" ")[1];
                logger.debug("请求文件：【{}】",filePath);
                writer = new PrintWriter(socket.getOutputStream());
                if (!new File(filePath).exists()) {
                    writer.println("HTTP/1.1 404");
                    writer.println("Server: User-Server");
                    writer.println("");
                    writer.println("找不到页面");
                }else{
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Server: User-Server");
                    // 渲染图片
                    if (filePath.endsWith("jpg") || filePath.endsWith("gif") || filePath.endsWith("png") || filePath.endsWith("ico")) {
                        fileInputStream = new FileInputStream(filePath);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        int i=0;
                        while ((i = fileInputStream.read()) != -1) {
                            byteArrayOutputStream.write(i);
                        }
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        writer.println("Content-Type: image/jpeg");
                        writer.println("Content-Length: "+ bytes.length);
                        writer.println("");
                        socket.getOutputStream().write(bytes,0,bytes.length);
                    }else{//普通html页面
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                        writer.println("Content-Type: text/html;charset=UTF-8");
                        writer.println("");
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            logger.debug(line);
                            writer.println(line);
                        }
                    }
                }
                writer.flush();
            } catch (IOException e) {
                logger.error("服务器错误：",e);
                writer.println("HTTP/1.1 500");
                writer.println("");
                writer.flush();
            } finally {
                close(fileInputStream,reader,socket);
            }
        }
    }

    /**
     * 关闭
     * @param closeables
     */
    private void close(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        HttpServer server = new SimpleHttpServer(8080);
        server.bootstrap();
    }
}
