package com.lxd.daily.pattern.interpreter.demo1;

/**
 * 距离表达式：终结符表达式 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class DistanceNode extends AbstractNode{
    /**
     * 具体距离，由构造器传入
     */
    private String distance;

    public DistanceNode(String distance) {
        if (distance == null) {
            throw new IllegalStateException("distance");
        }
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return distance;
    }
}
