package com.lxd.daily.pattern.filter;

import java.util.List;
import java.util.Map;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class SendTimeFilter extends EmailFilter{
    private String type;
    public SendTimeFilter(String type) {
        this.type  = type;
    }

    @Override
    public void doFilter(List<Object> results, Map<String, Object> condition) {
        System.out.println("根据发送时间过滤");
        if(next != null)
        next.doFilter(results,condition);
    }

    @Override
    String getType() {
        return type;
    }
}
