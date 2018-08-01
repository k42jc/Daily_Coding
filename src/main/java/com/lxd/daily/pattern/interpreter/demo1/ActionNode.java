package com.lxd.daily.pattern.interpreter.demo1;

/**
 * 动作表达式：终结符表达式 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class ActionNode extends AbstractNode{

    /**
     * 具体动作，由构造器传入
     */
    private String action;

    public ActionNode(String action) {
        if (action == null) {
            throw new IllegalStateException("action");
        }
        this.action = action;
    }

    @Override
    public String interpret() {
        switch (action.toLowerCase()) {
            case "move":
                return "移动";
            case "run":
                return "快速移动";
            default:
                return "无效指令";
        }
    }
}
