package com.lxd.daily.interview.mayijinfu.test2;

import com.lxd.daily.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * 文件工具类
 */
public final class FileUtils {

    /**
     * 将指定字符串拼接到指定文件末尾
     * @param filePath
     * @param content
     */
    public static void appendToFile(String filePath,String content){
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath");
        }
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
            int i=0;
            while(i<1000){// 写个大文件
                writer.newLine();
                writer.write(content);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        appendToFile("/Users/liaoxudong/Downloads/test2.txt","git1_git_2_3git4");
    }
}
