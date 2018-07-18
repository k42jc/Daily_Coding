package com.lxd.daily.pattern.bridge.impl;

/**
 * 桥接模式中的"实现一方"
 *
 * 负责从数据库中读取数据
 */
public interface DBOperate {

    String readData();
}
