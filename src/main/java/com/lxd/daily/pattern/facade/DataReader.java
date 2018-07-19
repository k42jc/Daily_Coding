package com.lxd.daily.pattern.facade;

/**
 * 数据读取接口
 *
 * 读取接口后再执行加密操作
 * Created by liaoxudong
 * Date:2018/7/19
 */

public interface DataReader {

    /**
     * 读取数据
     * @return 返回读取后的数据
     */
    String read();
}
