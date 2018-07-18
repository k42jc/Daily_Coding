package com.lxd.daily.pattern.bridge;

/**
 * 将数据库数据转换为txt格式文档
 */
public class TxtDataRepoter extends DataRepoter{

    @Override
    void repoter() {
        String s = dbOperate.readData();
        System.out.println(String.format("将%s到处为txt数据",s));
    }
}
