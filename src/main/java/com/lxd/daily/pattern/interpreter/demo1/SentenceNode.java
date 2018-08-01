package com.lxd.daily.pattern.interpreter.demo1;

/**
 * 简单语句解释：非终结符表达式 <br/>
 * 语句表达式固定包含三个命令：<br/>
 * <ol>
 *     <li>{@link DirectionNode} :方向表达式</li>
 *     <li>{@link ActionNode} :动作表达式</li>
 *     <li>{@link DistanceNode} :距离表达式</li>
 * </ol>
 * <br/>
 * <p>如：
 * <pre>
 *     LEFT MOVE 一米距离
 *     解释出来便是：向左移动一米距离
 * </pre>
 * </p>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class SentenceNode extends AbstractNode{
    /**
     * 方向表达式
     */
    private AbstractNode directionNode;
    /**
     * 动作表达式
     */
    private AbstractNode actionNode;
    /**
     * 距离表达式
     */
    private AbstractNode distanceNode;

    public SentenceNode(AbstractNode directionNode, AbstractNode actionNode, AbstractNode distanceNode) {
        this.directionNode = directionNode;
        this.actionNode = actionNode;
        this.distanceNode = distanceNode;
    }

    @Override
    public String interpret() {
        return directionNode.interpret() + actionNode.interpret() + distanceNode.interpret();
    }
}
