package com.lxd.daily.pattern.filter;

import java.util.List;
import java.util.Map;

/**
 * 根据邮件主题搜索邮件
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class SubjectFilter extends EmailFilter{

    private String type;
    public SubjectFilter(String type) {
        this.type = type;
    }

    @Override
    public void doFilter(List<Object> results, Map<String, Object> condition) {
        System.out.println("根据主题过滤");
        if(next != null)
        next.doFilter(results,condition);
    }

    @Override
    String getType() {
        return this.type;
    }
}
