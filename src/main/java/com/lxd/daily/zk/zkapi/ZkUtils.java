package com.lxd.daily.zk.zkapi;

import com.lxd.daily.util.ConfigUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 原生zk操作工具类
 * Created by liaoxudong on 2017/8/15.
 */
public class ZkUtils {
    private static final Logger logger = LoggerFactory.getLogger(ZkUtils.class);
    private static CountDownLatch latch = new CountDownLatch(1);
    private static String ZK_CONNECT_STR = ConfigUtils.PROP.getProp("zk.connect.cluster", "127.0.0.1:2181");
    private static int ZK_CONN_TIMEOUT = ConfigUtils.PROP.getProp("zk.connection.timeout.ms", Integer.class, 5000);
    private static ZooKeeper zooKeeper;

    static{
        try{
            zooKeeper = new ZooKeeper(ZK_CONNECT_STR, ZK_CONN_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                        logger.info("连接zk成功：target "+ZK_CONNECT_STR);
                        latch.countDown();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 设置超时时间 6秒还没连接成功
            latch.await(6000, TimeUnit.MILLISECONDS);
            if (zooKeeper == null) {
                logger.error("连接zk失败...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 节点是否存在
     * @param path 节点路径
     * @return boolean
     */
    public static boolean exists(String path){
        try {
            return zooKeeper.exists(path,null) != null;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建节点
     * @param path 创建节点的路径
     * @param data 节点数据
     * @param acls 权限列表
     * @param mode 节点类型：持久、临时等
     * @param sync 是否同步创建
     */
    public static void create(String path,byte[] data, List<ACL> acls,CreateMode mode,boolean sync){
        try {
            if (exists(path)) {
                logger.warn("{} 节点已存在!",path);
                return;
            }
            if(sync){
                // 同步创建节点
                String created = zooKeeper.create(path, data, acls, mode);
                if (created != null) {
                    logger.info("{} 节点创建成功！",created);
                }else{
                    logger.error("创建{} 节点失败！",path);
                }
            }else{
                zooKeeper.create(path, data, acls, mode, new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path2, Object ctx, String name) {
                        if (rc == 0) {
                            logger.info("节点{}创建成功！",path2);
                        }else{
                            logger.error("节点{}创建失败！",path);
                        }
                    }
                }, null);
            }
        } catch (Exception e) {
            logger.error("创建{}节点失败！",path,e);
        }
    }

    /**
     * 获取节点下的子节点列表
     * @param path 节点路径
     * @param sync 是否同步获取
     * @return 子节点列表
     */
    public static List<String> getChildren(String path,boolean sync) {
        if(!exists(path)){
            return null;
        }
        if (sync) {
            try {
                return zooKeeper.getChildren("/", null);
            } catch (Exception e) {
                logger.error("获取{}子节点为空！",path);
            }
        }else{
            try {
                ZkCountDownLatch zkCountDownLatch = new ZkCountDownLatch(1);
                //异步方式获取子节点列表
                zooKeeper.getChildren("/", null, new AsyncCallback.ChildrenCallback() {
                    @Override
                    public void processResult(int i, String s, Object o, List<String> list) {
                        if (i == 0) {
                            zkCountDownLatch.setData(list);
                            zkCountDownLatch.countDown();
                        }
                    }
                },null);
                // 5s超时时间 再拿不到返回空
                zkCountDownLatch.await(5000,TimeUnit.MILLISECONDS);
                return (List<String>) zkCountDownLatch.getData();
            } catch (Exception e) {
                logger.error("获取{}节点数据失败！",path);
            }
        }
        return null;
    }

    /**
     * 获取节点数据
     * @param path 节点路径
     * @param sync 是否同步
     * @return 节点数据
     */
    public static String getData(String path, boolean sync) {
        if (!exists(path)) {
            logger.warn("节点{}不存在！",path);
            return null;
        }
        if (sync) {
            try {
                byte[] data = zooKeeper.getData(path, null, null);
                return new String(data, "UTF-8");
            } catch (Exception e) {
                logger.error("获取{}节点数据失败！",path);
            }
        }else{
            try {
                ZkCountDownLatch zkCountDownLatch = new ZkCountDownLatch(1);
                zooKeeper.getData(path, null, new AsyncCallback.DataCallback() {
                    @Override
                    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                        if (i == 0) {
                            zkCountDownLatch.setData(bytes);
                        }else{
                            logger.error("获取{}节点数据失败！",path);
                        }
                        zkCountDownLatch.countDown();
                    }
                },null);
                zkCountDownLatch.await(5000, TimeUnit.MILLISECONDS);
                if (zkCountDownLatch.getData() != null) {
                    return new String((byte[])zkCountDownLatch.getData(),"UTF-8");
                }
            } catch (Exception e) {
                logger.error("获取{}节点数据失败！",path);
            }
        }
        return null;
    }

    /**
     * 更新节点最新版本的数据
     * @param path 节点路径
     * @param data 新数据
     * @param sync 是否同步
     * @return 是否更新成功
     */
    public static boolean setData(String path, byte[] data, boolean sync) {
        if(!exists(path)){
            logger.warn("节点{}不存在！",path);
            return false;
        }
        if (sync) {
            // 同步更新节点数据
            try {
                Stat stat = zooKeeper.setData(path, data, -1);
                if (stat != null) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("更新节点{}数据失败！",path);
            }
        }else{
            // 异步更新节点数据
            try {
                ZkCountDownLatch zkCountDownLatch = new ZkCountDownLatch(1);
                zooKeeper.setData(path, data, -1, new AsyncCallback.StatCallback() {
                    @Override
                    public void processResult(int i, String s, Object o, Stat stat) {
                        if (i== 0 && stat != null) {
                            zkCountDownLatch.setData(true);
                        }else{
                            zkCountDownLatch.setData(false);
                        }
                        zkCountDownLatch.countDown();
                    }
                },null);
                zkCountDownLatch.await(5000, TimeUnit.MILLISECONDS);
                return (boolean)zkCountDownLatch.getData();
            } catch (Exception e) {
                logger.error("更新节点{}数据失败！",path);
            }
        }
        return false;
    }

    /**
     * 删除节点的最新版本
     * @param path 节点路径
     * @param sync 是否同步删除
     * @return 是否删除成功
     */
    public static boolean delete(String path, boolean sync) {
        if(!exists(path)){
            logger.warn("节点{}不存在！",path);
            return false;
        }
        if (sync) {
            // 同步删除节点
            try {
                zooKeeper.delete(path, -1);//没有抛出异常表示删除成功
                return true;
            } catch (Exception e) {
                logger.error("删除节点{}失败!",path);
                return false;
            }
        } else{
            ZkCountDownLatch zkCountDownLatch = new ZkCountDownLatch(1);
            zooKeeper.delete(path, -1, new AsyncCallback.VoidCallback() {
                @Override
                public void processResult(int i, String s, Object o) {
                    if (i == 0) {
                        zkCountDownLatch.setData(true);
                    } else{
                        zkCountDownLatch.setData(false);
                    }
                    zkCountDownLatch.countDown();
                }
            },null);
            try {
                zkCountDownLatch.await(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return (boolean)zkCountDownLatch.getData();
        }

    }



    public static void main(String[] args) {
        /*String zkConnectStr = ConfigUtils.PROP.getProp("zk.connect.cluster", "127.0.0.1:2181");
        ZooKeeper zooKeeper = connect(zkConnectStr);
        System.out.println(zooKeeper.getState());


        try {
            // 同步创建节点
            if (zooKeeper.exists("/test", null) == null) {
                String path = zooKeeper.create("/test", "hello world!".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                System.out.println("同步创建节点："+path);
            }
            // 异步创建节点
            if (zooKeeper.exists("/test2", null) == null) {
                zooKeeper.create("/test2", "hello world2!".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        System.out.println("异步创建节点："+path);
                    }
                }, null);
            }
            // 同步方式获取子节点
            List<String> children = zooKeeper.getChildren("/", null);
            System.out.println("/根目录下节点列表：");
            children.forEach((child) -> {
                System.out.println(child);
            });

            //异步方式获取子节点列表
            zooKeeper.getChildren("/", null, new AsyncCallback.ChildrenCallback() {
                @Override
                public void processResult(int i, String s, Object o, List<String> list) {
                    System.out.println("/根目录下节点列表：");
                    list.forEach((key) -> {
                        System.out.println(key);
                    });
                }
            },null);
            // 同步获取节点数据
            byte[] data = zooKeeper.getData("/test", null, null);
            System.out.println("/test节点下的数据为："+new String(data,"UTF-8"));
            // 异步获取节点数据
            zooKeeper.getData("/test", null, new AsyncCallback.DataCallback() {
                @Override
                public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                    try {
                        System.out.println("/test节点下的数据为："+new String(bytes,"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            },null);
            // 同步更新节点数据
            Stat stat = zooKeeper.setData("/test", "test hello world!".getBytes(), -1);// 更新最新版本的数据传入-1
            if (stat != null) {
                System.out.println("/test节点数据更新成功！");
            }
            // 异步更新节点数据
            zooKeeper.setData("/test", "hahahahah".getBytes(), -1, new AsyncCallback.StatCallback() {
                @Override
                public void processResult(int i, String s, Object o, Stat stat) {
                    if (stat != null) {
                        System.out.println("/test节点数据更新成功！");
                    }
                }
            },null);
            // 同步删除节点
            zooKeeper.delete("/test2", -1);//没有抛出异常表示删除成功
            zooKeeper.delete("/test2", -1, new AsyncCallback.VoidCallback() {
                @Override
                public void processResult(int i, String s, Object o) {
                    if (i == 0) {
                        System.out.println("删除/test2成功！");
                    } else{
                        System.out.println("删除/test2节点失败");
                    }
                }
            },null);
            Thread.sleep(30000);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        /*Runnable runnable = new Runnable(){
            @Override
            public void run() {
                String data = getData("/test", false);
                logger.info("{}节点数据：{}", "/test",data);
            }
        };
        for(int i=0;i<100;i++) {
            new Thread(runnable, "Thread-zkClient-" + i).start();
        }*/
        create("/test2", "hehehhhe".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, false);
        String data = getData("/test2", true);
        System.out.println(data);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*List<String> children = getChildren("/", true);
        children.forEach((child) -> {
            System.out.println(child);
        });*/
    }
}
