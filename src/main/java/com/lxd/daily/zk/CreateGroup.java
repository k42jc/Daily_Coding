package com.lxd.daily.zk;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by liaoxudong on 2017/8/8.
 */
public class CreateGroup implements Watcher{

    private static final Logger logger = LoggerFactory.getLogger(CreateGroup.class);
    private static final int SESSION_TIMEOUT = 5000;
    private String hosts;

    private ZooKeeper zooKeeper;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 连接到zookeeper服务
     * @param hosts
     * @throws IOException
     * @throws InterruptedException
     */
    public void connect(String hosts) throws IOException, InterruptedException {
        this.hosts = hosts;
        zooKeeper = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        // 阻塞直到连接成功
        countDownLatch.await(SESSION_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            // 连接成功取消同步器
            logger.info("连接到zk服务器：{}，成功",hosts);
            countDownLatch.countDown();
        }
    }

    /**
     * 创建zookeeper组
     * @param groupName
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void createGroup(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createdPath = zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info("创建了zk组，所在路径：{}",createdPath);
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    public static void main(String[] args) {
        String hosts = "192.168.168.168:2181";
        CreateGroup createGroup = new CreateGroup();
        try {
            createGroup.connect(hosts);
            createGroup.createGroup("daily_coding");
            createGroup.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
