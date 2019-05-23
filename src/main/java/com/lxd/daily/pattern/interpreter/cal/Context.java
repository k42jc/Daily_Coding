package com.lxd.daily.pattern.interpreter.cal;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 维护数学表达式的上下文引用
 */
public class Context {

    private StringTokenizer expression;
    private String currentOperator;
    private List<String> ops = new ArrayList<>();
    /**
     * 运算符
     * 支持【加减乘除】【()括号优先级运算】
     *
     */
    private static final String OPERATORS = "+-*/()";

    public Context(String expression){
        this.expression = new StringTokenizer(expression, OPERATORS, true);
        while (this.expression.hasMoreTokens()) {
            ops.add(this.expression.nextToken());
        }
//        nextOperator();
    }

    public List<String> getOps(){
        return this.ops;
    }

    /**
     * 获取表达式中下一个可用计算单元
     * @return 可用运算符或数字
     */
    private String nextOperator(){
        boolean hasMoreTokens = expression.hasMoreTokens();
        if(hasMoreTokens){
            currentOperator = expression.nextToken();
        }else{
            currentOperator = null;
        }
        return currentOperator;
    }

    /**
     * 获取当前运算符或数字
     * @return 运算符或数字
     */
    public String currentOperator(){
        return this.currentOperator;
    }

    public Context skip(){
        nextOperator();
        return this;
    }

    /**
     * 如果当前为数字，则转换为bigDecimal后返回，方便运算操作
     * @return 字符串转换为数字
     */
    public BigDecimal currentNumber(){
        try {
            return new BigDecimal(currentOperator);
        } catch (Exception e) {
            throw new IllegalStateException("currentOperator not number");
        }
    }


    public static void main(String[] args){
        Context context = new Context("1+2*3-4/(8+9)");
        System.out.println(context.currentOperator());
        String s = context.skip().currentOperator();
        System.out.println(s);
        System.out.println(context.skip().currentNumber());
    }

}
