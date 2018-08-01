package com.lxd.daily.pattern.command.command;

import com.lxd.daily.pattern.command.receiver.BoardScreen;

/**
 * Created by liaoxudong
 * Date:2018/7/20
 */

public class CreateCommand implements Command {
    private BoardScreen boardScreen = new BoardScreen();
    @Override
    public void execute() {
        System.out.println("执行创建命令");
        boardScreen.create();
    }
}
