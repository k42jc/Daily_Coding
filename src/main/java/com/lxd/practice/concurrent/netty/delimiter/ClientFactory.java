package com.lxd.practice.concurrent.netty.delimiter;

import com.lxd.practice.exception.ExceptionCode;
import com.lxd.practice.exception.PracticeException;
import com.lxd.practice.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 *
 * 客户端操作工厂
 * Created by liaoxudong on 2017/7/28.
 */
public class ClientFactory {
    private final static Logger logger = LoggerFactory.getLogger(ClientFactory.class);

    /**
     * TCP客户端同步获取交互数据
     * @param msg
     * @param timeOut
     * @return
     * @throws Exception
     */
    public static Object sendMsg(ClientMsg msg, int timeOut) throws Exception{
        if (null == msg || StringUtils.isEmpty(msg.getHost())) {
            throw new PracticeException(ExceptionCode.ILLEGAL_PARAMETER);

        }
        ClientCountDownLatch latch = new ClientCountDownLatch(1);
        EchoClient.connect(msg,latch);
        boolean flag = latch.await(timeOut, TimeUnit.SECONDS);
        if (!flag) {
            logger.error("{} send timeout!",msg);
            throw new PracticeException(ExceptionCode.TCP_CONNECT_TIME_OUT);
        }

        return latch.getData();
    }
}
