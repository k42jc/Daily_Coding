package com.lxd.daily.pattern.bridge;

import com.lxd.daily.pattern.bridge.impl.MySQLOperate;
import com.lxd.daily.pattern.bridge.impl.OracleOperate;
import com.lxd.daily.pattern.bridge.impl.SqlServerOperate;

/**
 * 桥接模式客户端使用场景
 */
public class BrigeClient {

    public static void main(String[] args){
        // 将数据库数据转换为txt
        DataRepoter repoter = new TxtDataRepoter();
        repoter.setDbOperate(new MySQLOperate());
        repoter.repoter();// 将mysql数据库数据转换为txt
        repoter.setDbOperate(new OracleOperate());
        repoter.repoter();// 将oracle数据库数据转化为txt
        repoter.setDbOperate(new SqlServerOperate());
        repoter.repoter();// 将sqlServer数据库数据转换为txt
        // 将mysql数据库数据转换为xml
        repoter = new XmlDataRepoter();
        repoter.setDbOperate(new MySQLOperate());
        repoter.repoter();

        //将oracle数据库数据导出为json
        repoter = new JsonDataRepoter();
        repoter.setDbOperate(new OracleOperate());
        repoter.repoter();

    }
}
