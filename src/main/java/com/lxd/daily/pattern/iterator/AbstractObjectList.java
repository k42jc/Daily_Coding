package com.lxd.daily.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象聚合类
 * @author liaoxudong
 * @date 2018/8/1
 */

public abstract class AbstractObjectList {

    protected List<Object> objects = new ArrayList<>();

    public AbstractObjectList(List<Object> objects) {
        this.objects = objects;
    }

    public List<Object> getObjects() {
        return this.objects;
    }

    public void addObject(Object object){
        this.objects.add(object);
    }

    public void removeObject(Object object) {
        this.objects.remove(object);
    }

    /**
     * 创建迭代器的抽象工厂方法
     * @return 抽象迭代器
     */
    public abstract AbstractIterator createIterator();
}
