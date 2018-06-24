package com.lxd.daily.pattern.factory;

public interface IFileFactory {

    /**
     * 文件上传
     * @param files 文件二进制
     * @param fileName 文件名
     * @param fileType 文件类型
     * @param saveFolder 保存子目录
     * @return 文件下载的url
     */
    String upload(byte[] files,String fileName,String fileType,String saveFolder);

    /**
     * 文件下载
     * @param url 文件访问url
     */
    void download(String url);
}
