package com.lxd.daily.pattern.bridge;


import com.lxd.daily.pattern.bridge.impl.DBOperate;

/**
 * 桥接模式中的抽象一方
 *
 * 保留数据库操作的引用，从数据库接口获取数据，然后执行对应的转换操作
 */
public abstract class DataRepoter {

    protected DBOperate dbOperate;

    public void setDbOperate(DBOperate dbOperate) {
        this.dbOperate = dbOperate;
    }

    /**
     * 具体数据导出由不同子类去实现
     */
    abstract void repoter();


}
