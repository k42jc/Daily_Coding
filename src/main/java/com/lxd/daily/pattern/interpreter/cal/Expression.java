package com.lxd.daily.pattern.interpreter.cal;

import java.math.BigDecimal;

/**
 * 表达式接口
 */
public interface Expression {

    /**
     * 解析数学表达式
     * @return 计算结果
     */
    String interpret();

}
