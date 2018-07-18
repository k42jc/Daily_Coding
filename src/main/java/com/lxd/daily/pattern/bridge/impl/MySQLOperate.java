package com.lxd.daily.pattern.bridge.impl;

public class MySQLOperate implements DBOperate {
    @Override
    public String readData() {
        return "MySql 数据库数据";
    }
}
