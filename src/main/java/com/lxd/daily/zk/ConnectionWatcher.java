package com.lxd.daily.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 用于与zk服务建立连接
 * Created by liaoxudong on 2017/8/8.
 */
public class ConnectionWatcher implements Watcher{

    private static final Logger logger = LoggerFactory.getLogger(ConnectionWatcher.class);

    private static final int SESSION_TIMEOUT = 5000;

    private ZooKeeper zk;
    private CountDownLatch latch = new CountDownLatch(1);
    private String hosts;

    public void connect(String hosts) {
        try {
            this.hosts = hosts;
            zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
            latch.await(SESSION_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            logger.info("连接成功：{}",hosts);
            latch.countDown();
        }
    }

    public void close(){
        try {
            zk.close();
            logger.warn("连接关闭：{}",hosts);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
