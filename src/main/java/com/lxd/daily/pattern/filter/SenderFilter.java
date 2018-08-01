package com.lxd.daily.pattern.filter;

import java.util.List;
import java.util.Map;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class SenderFilter extends EmailFilter {
    private String type;
    public SenderFilter(String type) {
        this.type= type;
    }

    @Override
    public void doFilter(List<Object> results, Map<String, Object> condition) {
        System.out.println("根据发送者过滤");
        if(next != null)
        next.doFilter(results,condition);
    }

}
