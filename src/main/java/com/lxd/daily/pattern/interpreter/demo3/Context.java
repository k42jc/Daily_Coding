package com.lxd.daily.pattern.interpreter.demo3;

import java.util.StringTokenizer;

/**
 * 环境类：用于存储和操作用于解释的语句 <br/>
 *
 * 在本实例中每个需要解释的单词可以称为一个动作标记或命令
 * @author liaoxudong
 * @date 2018/7/31
 */

public class Context {
    /**
     * 用于将字符串分解为更新的字符串标记，默认情况下以空格作为分隔符
     */
    private StringTokenizer tokenizer;
    private String currentToken;

    public Context(String expression) {
        this.tokenizer = new StringTokenizer(expression);
        nextToken();
    }

    /**
     * 获取下一个命令
     * @return 下一个命令
     */
    public String nextToken(){
        if (tokenizer.hasMoreTokens()) {
            currentToken = tokenizer.nextToken();
        } else{// 此处需要赋值为null，否则在最后一个元素始终无法跳过
            currentToken = null;
        }
        return currentToken;
    }

    /**
     * 获取当前命令
     * @return 当前命令
     */
    public String currentToken(){
        return currentToken;
    }

    /**
     * 如果目标命令与当前命令一致，则跳过
     * @param token 目标命令
     */
    public void skipToken(String token) {
        if (!token.equals(currentToken)) {
            throw new IllegalStateException("currentToken not same with target token");
        }
        nextToken();
    }

    /**
     * 跳过当前token
     */
    public void skip(){
        nextToken();
    }

    /**
     * 如果当前命令为数字，则转换后返回
     * @return 目标数字
     */
    public int currentNumber(){
        try {
            return Integer.valueOf(currentToken);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("currentToken not a number");
        }
    }

    /*public static void main(String[] args) {
        String expression = "20+12-2*(1+3)+4/2";

        StringTokenizer tokenizer = new StringTokenizer(expression, "+-\\*\\/()", true);
        System.out.println(tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }*/

}
