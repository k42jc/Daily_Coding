package com.lxd.practice.zk.curator;

import com.lxd.practice.util.ConfigUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * zk操作客戶端
 * Created by liaoxudong on 2017/8/9.
 */
public class ZKClient {
    private static final String ZK_CONNECT_ADDR = ConfigUtils.PROP.getProp("zk.connect.addr");
    private static final int ZK_SESSION_TIMEOUT_MS = ConfigUtils.PROP.getProp("zk.session.timeout.ms",Integer.class);
    private static final int ZK_CONNCTION_TIMEOUT_MS = ConfigUtils.PROP.getProp("zk.connection.timeout.ms",Integer.class);

    private static CuratorFramework client = CuratorFrameworkFactory.builder().connectString(ZK_CONNECT_ADDR)
            .sessionTimeoutMs(ZK_SESSION_TIMEOUT_MS)
            .connectionTimeoutMs(ZK_CONNCTION_TIMEOUT_MS)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))//重连间隔时间1s 重连次数3次
            .build();

    public static void main(String[] args) throws Exception {
        client = CuratorFrameworkFactory.newClient(ZK_CONNECT_ADDR, ZK_SESSION_TIMEOUT_MS, ZK_CONNCTION_TIMEOUT_MS, new ExponentialBackoffRetry(1000, 3));
//        CuratorFrameworkFactory.builder().connectString()
//        System.out.println(ZK_CONNECT_ADDR);
        String path = "/opsoa/com.xinguodu.openplatform.common.soa.protocol.IProtocol";
        client.start();
        List<String> strings = client.getChildren().forPath(path);
        for (String s : strings) {
            System.out.println(s);
            GetDataBuilder data = client.getData();
            byte[] bytes = data.forPath(path+"/"+s);
            System.err.println(new String(bytes));
        }
        client.close();
    }
}
