package com.lxd.daily.pattern.interpreter.demo3;

/**
 * 抽象节点
 * @author liaoxudong
 * @date 2018/7/31
 */

public interface Node {

    /**
     * 解析语句
     * @param context 目标语句上下文
     */
    void interpret(Context context);

    /**
     * 用于执行解析完成后的指令
     */
    void execute();
}
