package com.lxd.daily.pattern.interpreter.demo1;

import java.util.Stack;

/**
 * 指令处理类 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class InstructionHandler {

    /**
     * 指令解释器
     */
    private AbstractNode node;

    /**
     * 指令拆解处理
     * @param instruction 指令字符串
     */
    public InstructionHandler handle(String instruction) {
        if (instruction == null) {
            throw new IllegalArgumentException("instruction");
        }
        AbstractNode left,right;
        AbstractNode direction,action,distance;
        String[] expressions = instruction.split(" ");
        Stack<AbstractNode> stack = new Stack<>();
        // 遍历操作表达式串
        for(int i=0;i<expressions.length;i++) {
            // 如果是and命令，需要将接下来的三个表达式作为AndNode的right，栈顶元素作为left
            if ("and".equalsIgnoreCase(expressions[i])) {
                direction = new DirectionNode(expressions[++i]);
                action = new ActionNode(expressions[++i]);
                distance = new DistanceNode(expressions[++i]);
                right = new SentenceNode(direction, action, distance);
                left = stack.pop();
                stack.push(new AndNode(left, right));
            }else{// 如果不是and命令，表示从头开始解释，则将前三个命令组装为简单语句表达式，压入栈
                direction = new DirectionNode(expressions[i]);
                action = new ActionNode(expressions[++i]);
                distance = new DistanceNode(expressions[++i]);
                left = new SentenceNode(direction, action, distance);
                stack.push(left);
            }
        }
        // 最终处理完成的只有栈顶的一个表达式，调用解释方法即可
        node = stack.pop();
        return this;
    }

    /**
     * 解释入口
     * @return 解释结果
     */
    public String interpret(){
        return this.node.interpret();
    }
}
