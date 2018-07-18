package com.lxd.daily.pattern.bridge.impl;

public class OracleOperate implements DBOperate {
    @Override
    public String readData() {
        return "Oracle 数据库数据";
    }
}
