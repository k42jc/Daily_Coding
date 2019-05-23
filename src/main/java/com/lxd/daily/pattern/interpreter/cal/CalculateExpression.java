package com.lxd.daily.pattern.interpreter.cal;


import java.util.List;
import java.util.Stack;

/**
 * 完整的计算表达式
 */
public class CalculateExpression implements Expression {
    private Object[] expressions;

    public CalculateExpression(Context context) {
        this.expressions = context.getOps().toArray();
    }
    @Override
    public String interpret() {
        Stack<Expression> stack = new Stack<>();
        for (int i=0;i<expressions.length;i++) {
            String expression = expressions[i].toString();
            if(!expression.matches("^\\d+$")){//如果当前为运算符，则需要将栈顶元素弹出来与下一个元素计算结果
                Expression pop = stack.pop();
                String left = pop.interpret();
                String right = expressions[++i].toString();
                Expression result = new OperatorExpression(left, right, expression);
                stack.push(result);
            }else{// 如果是数字，则拿出来计算即可
                String op = expressions[++i].toString();
                String right = expressions[++i].toString();
                Expression result = new OperatorExpression(expression, right, op);
                stack.push(result);// 将结果压入栈
            }
        }
        return stack.pop().interpret();
    }

}
