package com.lxd.daily.interview.mayijinfu.test2;

import com.lxd.daily.interview.mayijinfu.StringUtils;
import com.lxd.daily.interview.mayijinfu.ThreadPoolFactory;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JAVA中对文件读取的效率会受到文件大小本身的影响，本题目要求能够对于文本文件进行读取，在保证读取效率的同时，要求内存不能溢出。
 要求：
 1.输入一个本地文本文件地址，文本文件大小为2G,文本编码类型为utf-8。
 2.读取该文本文件中出现特定单词的数量
 3.把文本部分文件读取到内存中后，即可释放内存，并统计特定单词出现次数和总时间耗时
 4. 尽量减低字符统计耗时。
 */
public class ReadBigFile implements FileRead{
    // 阻塞队列，用于存放读取到的文本行 最多同时存100行
    private static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
    private long startTime;

    @Override
    public FileRead read(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath");
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalStateException("file not exists");
        }
        // 读取开始时间
        startTime = System.currentTimeMillis();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String line;
            while ((line = reader.readLine()) != null){
//                System.out.println(line);
                count("git");
                while (!queue.offer(line)) {
                    System.out.println("读取暂停中...");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
    // 用于统计出现次数，需要考虑原子性
    private volatile AtomicInteger count = new AtomicInteger(0);


    public int getCount(){
        return this.count.get();
    }

    /**
     * 统计指定单词出现次数
     * @param specialWord 给定单词
     * @return
     */
    public int count(String specialWord) {
        for(int i=0;i<9;i++) {
            ThreadPoolFactory.submit(() -> {
               // 开启9条读线程，从队列中获取数据统计结果
                while (queue.size() > 0) {
                    try {
                        String line = queue.take();
                        queue.remove(line);
                        count.getAndAdd((line.split(specialWord).length - 1));
//                        System.out.println(count.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        while (queue.size() > 0) {
            System.out.println("统计中。。。");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间：" + (endTime - startTime));
        return count.get();
    }


    public static void main(String[] args){
        ReadBigFile read = new ReadBigFile();
        /*int count = */read.read("/Users/liaoxudong/Downloads/test2.txt");//.count("git");
        System.out.println(read.getCount());
//        System.out.println(count);
    }
}
