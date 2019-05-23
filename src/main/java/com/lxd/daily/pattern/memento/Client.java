package com.lxd.daily.pattern.memento;

/**
 * 备忘录模式客户端调用示例
 */
public class Client {

    public static void main(String[] args){
        // 状态管理器
        MementoCaretaker caretaker = new MementoCaretaker();
        // 棋子类
        Chessman chessman = new Chessman("车", 1, 1);
        display(chessman);
        // 保存状态
        caretaker.setMemento(chessman.save());
        // 水平移动棋子
        chessman.setX(2);
        display(chessman);
        caretaker.setMemento(chessman.save());
        chessman.setX(5);
        display(chessman);
        // 悔棋操作
        chessman.restore(caretaker.getMemento());
        display(chessman);

    }

    private static void display(Chessman chessman) {
        System.out.println("棋子：" + chessman.getLabel() + ",当前位置为：x=" + chessman.getX() + "，y=" + chessman.getY());
    }
}
