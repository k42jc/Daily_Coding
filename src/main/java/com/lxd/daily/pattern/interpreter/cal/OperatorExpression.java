package com.lxd.daily.pattern.interpreter.cal;

import java.math.BigDecimal;

public class OperatorExpression implements Expression{
    /**
     * 运算符左侧表达式
     */
    private String left;
    /**
     * 运算符右侧表达式
     */
    private String right;

    /**
     * 运算符
     */
    private String operator;

    public OperatorExpression(String left, String right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
    @Override
    public String interpret() {
        switch (operator) {
            case "+":
                return new BigDecimal(left).add(new BigDecimal(right)).toString();
            case "-":
                return new BigDecimal(left).subtract(new BigDecimal(right)).toString();
            case "*":
                return new BigDecimal(left).multiply(new BigDecimal(right)).toString();
            case "/":
                return new BigDecimal(left).divide(new BigDecimal(right)).toString();
        }
        return null;
    }

}
