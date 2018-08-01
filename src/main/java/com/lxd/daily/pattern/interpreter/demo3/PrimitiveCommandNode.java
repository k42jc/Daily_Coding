package com.lxd.daily.pattern.interpreter.demo3;

/**
 * 基本命令节点
 * <br/>
 * 终结符表达式
 * @author liaoxudong
 * @date 2018/7/31
 */

public class PrimitiveCommandNode implements Node {
    /**
     * 命令
     */
    private String name;
    /**
     * 具体要执行的目标字符串
     */
    private String text;

    @Override
    public void interpret(Context context) {
        name = context.currentToken();
        context.skip();
        if (name.equalsIgnoreCase("print")) {
            text = context.currentToken();
            context.skip();
        }
    }

    @Override
    public void execute() {
        if (name.equalsIgnoreCase("print")) {
            System.out.print(text);
        } else if (name.equalsIgnoreCase("space")) {
            System.out.print(" ");
        } else if (name.equalsIgnoreCase("break")) {
            System.out.println();
        }
    }
}
