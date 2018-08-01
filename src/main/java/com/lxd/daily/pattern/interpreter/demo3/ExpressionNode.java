package com.lxd.daily.pattern.interpreter.demo3;

import java.util.ArrayList;
import java.util.List;

/**
 * 表达式节点 <br/>
 *
 * 非终结符表达式
 * @author liaoxudong
 * @date 2018/7/31
 */

public class ExpressionNode implements Node{
    /**
     * 定义一个集合用于存储多个节点命令
     */
    private List<Node> list = new ArrayList<>();

    @Override
    public void interpret(Context context) {
        while (true) {
            if (context.currentToken() == null) {
                // 结束解析
                break;
            } else if ("end".equalsIgnoreCase(context.currentToken())) {
                // 结束loop循环
                context.skip();
                break;
            } else {// 目标需要解析的标记
                CommandNode node = new CommandNode();
                node.interpret(context);
                list.add(node);
            }
        }
    }

    @Override
    public void execute() {
        // 执行命令集合
        for (Node node : list) {
            node.execute();
        }

    }
}
