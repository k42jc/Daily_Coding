package com.lxd.practice.datasource;

import java.lang.annotation.*;

/**
 * 用于标记使用的数据源
 * Date:2017/10/26
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
