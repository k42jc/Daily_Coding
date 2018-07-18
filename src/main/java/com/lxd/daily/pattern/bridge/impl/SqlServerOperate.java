package com.lxd.daily.pattern.bridge.impl;

public class SqlServerOperate implements DBOperate {
    @Override
    public String readData() {
        return "SqlServer 数据库数据";
    }
}
