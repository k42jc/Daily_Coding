package com.lxd.daily.pattern.interpreter.demo2;

/**
 * 运算符表达式 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class OperatorExpression implements Expression{
    private Expression left;
    private Expression right;
    private String operator;

    public OperatorExpression(Expression left, Expression right,String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double interpret() {
        switch (operator) {
            case "+":
                return left.interpret() + right.interpret();
            case "-":
                return left.interpret() - right.interpret();
            default:
                return 0;
        }
    }
}
