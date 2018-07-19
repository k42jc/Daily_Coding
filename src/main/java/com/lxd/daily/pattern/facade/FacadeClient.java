package com.lxd.daily.pattern.facade;

/**
 *
 * 门面模式客户端调用示例
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class FacadeClient {

    public static void main(String[] args) {
        // 具体facade类中已经初始化了需要调用的每个步骤的对象，客户端只要调用外观接口给出的方法即可，不需要关心太多子系统逻辑
        EncryptFacade facade = new NormalEncryptFacade();
        facade.doIncrypt();
    }
}
