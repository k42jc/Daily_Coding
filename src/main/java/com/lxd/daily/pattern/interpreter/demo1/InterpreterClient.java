package com.lxd.daily.pattern.interpreter.demo1;

/**
 * 解释器模式客户端调用示例 <br/>
 *
 * @author liaoxudong
 * @date 2018/7/30
 */

public class InterpreterClient {

    public static void main(String[] args) {
        String expression = "up move 5 and down run 7 and right move 9";
        InstructionHandler instructionHandler = new InstructionHandler();
        String interpret = instructionHandler.handle(expression).interpret();
        System.out.println(interpret);
    }
}
