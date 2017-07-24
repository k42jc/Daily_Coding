package com.lxd.practice.jvm.class_load;

import java.io.IOException;
import java.io.InputStream;

/**
 * <h2>演示不同的类加载器对 <i>instanceof</i> 关键字的影响</h2>
 *
 * 运行下列程序输出：
 * <pre>
 *     class com.techeffic.blog.example.jvm.class_load.ClassLoadTest
 *     false
 * </pre>
 *
 * 此时
 * <br>
 * Created by liaoxudong on 2017/7/20.
 */
public class ClassLoadTest {


    public static void main(String[] args) {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream resourceAsStream = getClass().getResourceAsStream(fileName);
                    if (resourceAsStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[resourceAsStream.available()];
                    resourceAsStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        try {
            Object obj  = loader.loadClass("com.techeffic.blog.example.jvm.class_load.ClassLoadTest").newInstance();
            System.out.println(obj.getClass());
            System.out.println(obj instanceof com.lxd.practice.jvm.class_load.ClassLoadTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
