package com.lxd.daily.pattern.bridge;


/**
 * 将数据库数据到处为xml格式
 */
public class XmlDataRepoter extends DataRepoter{

    @Override
    void repoter() {
        String s = dbOperate.readData();
        System.out.println(String.format("将%s到处为xml格式",s));
    }
}
