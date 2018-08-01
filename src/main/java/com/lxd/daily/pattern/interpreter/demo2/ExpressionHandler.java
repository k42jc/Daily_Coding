package com.lxd.daily.pattern.interpreter.demo2;

import java.util.Stack;

/**
 * 简单加减法计算器 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/31
 */

public class ExpressionHandler {

    private Expression expression;

    public ExpressionHandler handle(String express){
        // 所有数字
        String[] numbers = express.split(" ");
        Stack<Expression> stack = new Stack<>();
        for(int i=0;i<numbers.length;i++) {
            if (!numbers[i].matches("^\\d+$")) {// 如果不是数字，表明是运算符，则需要组建表达式，从栈顶弹出元素作为左表达式，数组下一个元素作为右侧表达式
                String op = numbers[i];
                Expression left = stack.pop();
                Expression right = new NumberExpression(numbers[++i]);
                Expression expression = new OperatorExpression(left, right, op);
                // 将表达式压入栈
                stack.push(expression);
            }else{
                // 如果从头开始，那么一定是【数字+运算符+数字】，直接组装表达式压入栈即可
                Expression left = new NumberExpression(numbers[i]);
                String op = numbers[++i];
                Expression right = new NumberExpression(numbers[++i]);
                Expression expression = new OperatorExpression(left, right, op);
                stack.push(expression);
            }
        }
        // 最后栈顶保存的是结果表达式
        expression = stack.pop();
        return this;
    }

    public double interpret(){
        return expression.interpret();
    }

    public static void main(String[] args) {
        double interpret = new ExpressionHandler().handle("1 + 2 - 3 + 10000 - 20").interpret();
        System.out.println(interpret);
    }
}
