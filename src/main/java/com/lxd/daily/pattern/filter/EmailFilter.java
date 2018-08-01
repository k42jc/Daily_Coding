package com.lxd.daily.pattern.filter;

/**
 * 邮件查找抽象类
 * Created by liaoxudong
 * Date:2018/7/19
 */

public abstract class EmailFilter implements Filter{

    protected Filter next;

    @Override
    public void add(Filter filter) {
        next = filter;
    }

}
