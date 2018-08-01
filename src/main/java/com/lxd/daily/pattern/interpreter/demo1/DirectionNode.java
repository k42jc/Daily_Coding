package com.lxd.daily.pattern.interpreter.demo1;

/**
 * 方向符表达式：终结符表达式 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class DirectionNode extends AbstractNode{
    /**
     * 具体方向 由构造器传入
     */
    private String direction;

    public DirectionNode(String direction) {
        if (direction == null) {
            throw new IllegalStateException("direction");
        }
        this.direction = direction;
    }

    @Override
    public String interpret() {
        switch (direction.toLowerCase()) {
            case "up":
                return "向上";
            case "down":
                return "向下";
            case "left":
                return "向左";
            case "right":
                return "向右";
            default:
                return "";

        }
    }
}
