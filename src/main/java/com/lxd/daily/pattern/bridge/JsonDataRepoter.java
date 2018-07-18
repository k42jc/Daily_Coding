package com.lxd.daily.pattern.bridge;

public class JsonDataRepoter extends DataRepoter{

    @Override
    void repoter() {
        String s = dbOperate.readData();
        System.out.println(String.format("将%s导出为json数据",s));
    }
}
