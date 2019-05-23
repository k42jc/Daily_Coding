package com.lxd.daily.interview.mayijinfu.test2;

public interface FileRead {

    /**
     * 根据文件路径读取文件
     * @param filePath 文件路径
     * @return 本对象
     */
    FileRead read(String filePath);

    /**
     * 统计给定的字符串出现的次数
     * @param specialWord 给定单词
     * @return 出现次数
     */
    int count(String specialWord);
}
