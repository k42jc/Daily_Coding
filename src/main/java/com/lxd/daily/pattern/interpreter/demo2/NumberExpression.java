package com.lxd.daily.pattern.interpreter.demo2;

/**
 * 数字表达式 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class NumberExpression implements Expression {

    private double number;

    public NumberExpression(String number) {
        if(number == null){
            throw new IllegalStateException("number");
        }
        try {
            this.number = Double.valueOf(number);
        } catch (Exception e) {
            throw new IllegalStateException("number");
        }
    }

    @Override
    public double interpret() {
        return number;
    }
}
