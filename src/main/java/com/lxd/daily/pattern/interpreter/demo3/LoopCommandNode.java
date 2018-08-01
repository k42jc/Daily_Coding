package com.lxd.daily.pattern.interpreter.demo3;

/**
 * 循环命令节点
 *
 * 非终结符表达式
 * @author liaoxudong
 * @date 2018/7/31
 */

public class LoopCommandNode implements Node {
    /**
     * 循环次数
     */
    private int number;
    /**
     * 命令节点
     */
    private Node commandNode;

    @Override
    public void interpret(Context context) {
        String currentToken = context.currentToken();
        if (!currentToken.equalsIgnoreCase("loop")) {
            throw new IllegalStateException("not loop");
        }
        context.skip();
        number = context.currentNumber();
        context.skip();
        commandNode = new ExpressionNode();
        commandNode.interpret(context);
    }

    @Override
    public void execute() {
        for(int i=0;i<number;i++) {
            commandNode.execute();
        }
    }
}
