package com.lxd.daily.pattern.factory;

/**
 * 阿里云oos文件上传下载实现
 */
public class IAliyunFileFactory implements IFileFactory{

    @Override
    public String upload(byte[] files, String fileName, String fileType, String saveFolder) {
        return null;
    }

    @Override
    public void download(String url) {

    }
}
