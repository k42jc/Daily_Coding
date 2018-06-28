package com.lxd.daily.pattern.factory;

/**
 * 本地文件上传下载实现
 */
public class ILocalFileFactory implements IFileFactory{

    @Override
    public String upload(byte[] files, String fileName, String fileType, String saveFolder) {
        return null;
    }

    @Override
    public void download(String url) {
        // 设置HttpServletResponse 文件类型的响应头
        setHeaders();
        // 文件读取
        byte[] fileBytes = readFile(url);
        // writeAndFlush
    }

    protected byte[] readFile(String url) {
        return null;
    }

    protected void setHeaders() {
    }
}
