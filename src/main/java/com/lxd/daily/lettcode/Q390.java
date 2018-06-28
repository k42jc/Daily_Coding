package com.lxd.daily.lettcode;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q390 implements Serializable{

    public int lastRemaining(int n) {
        if(n<=1){
            return n;
        }
        List<Integer> nums = new ArrayList<>(n);
        for(int index=1;index<=n;index++){
            nums.add(index);
        }
        return delete(nums);
    }

    private int delete(List<Integer> nums){
        List<Integer> resultList = new ArrayList<>();
        for(int index=1;index<nums.size();index+=2){// 从第一个开始 隔一个数删除一个 就是删除下标为0、2、4...，最后保留下标为1、3、5...
            resultList.add(nums.get(index));
        }
        if(resultList.size() > 1){// 如果得到的结果集长度不为1 翻转后继续操作
            Collections.reverse(resultList);
            return delete(resultList);
        }
        // 只有一个元素的时候，返回即可
        return resultList.get(0);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*int i = new Q390().lastRemaining(1000);
        System.out.println(i);*/

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(arrayOutputStream);
        outputStream.writeObject("x");
        String s = arrayOutputStream.toString("ISO-8859-1");
        arrayOutputStream.close();
        outputStream.close();

//        byte[] bytes = s.getBytes();
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(s.getBytes("ISO-8859-1"));
        ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
        Object o = inputStream.readObject();
        System.out.println(o.toString());
        inputStream.close();

    }
}
