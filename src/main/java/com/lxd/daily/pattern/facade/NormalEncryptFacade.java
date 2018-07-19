package com.lxd.daily.pattern.facade;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class NormalEncryptFacade implements EncryptFacade{
    // FIXME 根据实际情况初始化
    private DataReader dataReader;
    private DataEncrypt dataEncrypt;
    private DataWriter dataWriter;

    @Override
    public void doIncrypt() {
        String msg = dataReader.read();
        String encrypt = dataEncrypt.encrypt(msg);
        dataWriter.write(encrypt);
    }
}
