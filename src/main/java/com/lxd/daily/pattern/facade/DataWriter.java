package com.lxd.daily.pattern.facade;

/**
 *
 * 数据写入接口
 *
 * 用于将加密后的数据写入
 * Created by liaoxudong
 * Date:2018/7/19
 */

public interface DataWriter {

    /**
     * 将密文写入
     * @param encryptedMsg
     */
    void write(String encryptedMsg);
}
