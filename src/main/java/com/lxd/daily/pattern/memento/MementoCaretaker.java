package com.lxd.daily.pattern.memento;

/**
 * 负责人类，象棋棋子状态管理类
 */
public class MementoCaretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
