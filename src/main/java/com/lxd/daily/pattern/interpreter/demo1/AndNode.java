package com.lxd.daily.pattern.interpreter.demo1;

/**
 * <strong>and</strong>命令解释器：非终结符表达式 <br>
 * @author liaoxudong
 * @date 2018/7/30
 */

public class AndNode extends AbstractNode{
    /**
     * and关键字左侧的表达式
     */
    private AbstractNode left;
    /**
     * and关键字右侧表达式
     */
    private AbstractNode right;

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpret() {
        return this.left.interpret() + " 再 " + this.right.interpret();
    }
}
