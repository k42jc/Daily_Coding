package com.lxd.daily.pattern.command.command;

import com.lxd.daily.pattern.command.receiver.BoardScreen;

/**
 * Created by liaoxudong
 * Date:2018/7/20
 */

public class EditCommand implements Command{

    private BoardScreen boardScreen = new BoardScreen();
    @Override
    public void execute() {
        System.out.println("执行编辑命令");
        boardScreen.edit();
    }

    public static void main(String[] args) {
        System.out.println(5&6);
        System.out.println(6%5);

        synchronized (EditCommand.class) {
            System.out.println("xxx");

        }
    }


}
