package com.lxd.daily.pattern.interpreter.demo3;

/**
 * 语句命令节点
 * @author liaoxudong
 * @date 2018/7/31
 */

public class CommandNode implements Node {
    private Node node;
    @Override
    public void interpret(Context context) {
        // 处理loop命令
        if (context.currentToken().equalsIgnoreCase("loop")) {
            node = new LoopCommandNode();
            node.interpret(context);
        }else{// 处理终结符命令
            node = new PrimitiveCommandNode();
            node.interpret(context);
        }
    }

    @Override
    public void execute() {
        node.execute();
    }
}
