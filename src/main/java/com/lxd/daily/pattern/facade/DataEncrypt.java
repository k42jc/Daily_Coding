package com.lxd.daily.pattern.facade;

/**
 *
 * 数据加密
 *
 * 将读取后的数据进行加密操作
 * Created by liaoxudong
 * Date:2018/7/19
 */

public interface DataEncrypt {

    /**
     * 将数据加密后返回密文
     * @param msg
     * @return
     */
    String encrypt(String msg);
}
