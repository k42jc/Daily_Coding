package com.lxd.daily.pattern.filter;

import java.util.List;
import java.util.Map;

/**
 *
 * 过滤接口
 * Created by liaoxudong
 * Date:2018/7/19
 */

public interface Filter {

    /**
     * 定义过滤方法，用于在链中对象执行结果过滤操作
     */
    void doFilter(List<Object> results,Map<String,Object> condition);

    void add(Filter filter);
}
