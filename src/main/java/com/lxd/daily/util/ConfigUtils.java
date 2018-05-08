package com.lxd.daily.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置工具类
 * Created by liaoxudong on 2017/8/9.
 */
public enum ConfigUtils {
    PROP(".properties"){
        @Override
        public String getProp(String key) {
            return getProp(key,"");
        }

        @Override
        public String getProp(String key, String defaultValue) {
            return properties.getProperty(key,"");
        }

        @Override
        public <T> T getProp(String key, Class<T> clazz) {
            String value = getProp(key);
            if ("Integer".equalsIgnoreCase(clazz.getSimpleName())) {
                return (T)Integer.valueOf(value);
            }
            if ("Long".equalsIgnoreCase(clazz.getSimpleName())) {
                return (T)Long.valueOf(value);
            }
            if ("Double".equalsIgnoreCase(clazz.getSimpleName())) {
                return (T)Double.valueOf(value);
            }
            return null;
        }

        @Override
        public <T> T getProp(String key, Class<T> clazz, T defaultValue) {
            String value = getProp(key);
            if(value == null || "".equals(value)){
                return defaultValue;
            }
            if ("Integer".equalsIgnoreCase(clazz.getSimpleName())) {
                return (T)Integer.valueOf(value);
            }
            if ("Long".equalsIgnoreCase(clazz.getSimpleName())) {
                return (T)Long.valueOf(value);
            }
            if ("Double".equalsIgnoreCase(clazz.getSimpleName())) {
                return (T)Double.valueOf(value);
            }
            return null;
        }
    };

    private String fileType;
    protected Properties properties;

    ConfigUtils(String fileType) {
        this.fileType = fileType;

        properties = new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        try {
            loadPropFile(properties,new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件加载到properties
     * @param properties
     * @param file
     */
    private void loadPropFile(Properties properties, File file) throws IOException {
        if (file != null && file.exists()) {
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (File f : files) {
                    if(f.isDirectory()){
                        loadPropFile(properties,f);
                    }else if(f.getName().endsWith(this.fileType)){
                        f.setReadable(true);
                        properties.load(new FileInputStream(f));
                    }
                }
            }else if(file.getName().endsWith(this.fileType)){
                file.setReadable(true);
                properties.load(new FileInputStream(file));
            }
        }
    }

    public abstract String getProp(String key);
    public abstract String getProp(String key,String defaultValue);
    public abstract <T> T getProp(String key,Class<T> clazz);
    public abstract <T> T getProp(String key,Class<T> clazz,T defaultValue);

}
