package com.lxd.daily.pattern.interpreter.cal;

/**
 * 简单加减法计算器客户端
 */
public class Client {

    public static void main(String[] args){
        String expression = "1+2-3+100+20-35*10";
        Context context = new Context(expression);
        Expression exp = new CalculateExpression(context);
        String result = exp.interpret();
        System.out.println(result);


        String e = "(1+2)/3-3*4";

        OperatorExpression operatorExpression = new OperatorExpression(
                new OperatorExpression(new OperatorExpression("1", "2", "+").interpret(), "3", "/").interpret(),
                new OperatorExpression("3", "4", "*").interpret(),
                "-");
        System.out.println(operatorExpression.interpret());

    }
}
