package com.lxd.daily.pattern.command;

import com.lxd.daily.pattern.command.command.CreateCommand;
import com.lxd.daily.pattern.command.command.EditCommand;
import com.lxd.daily.pattern.command.command.OpenCommand;

/**
 * 命令模式客户端调用示例
 * Created by liaoxudong
 * Date:2018/7/20
 */

public class CommandClient {

    public static void main(String[] args) {
        Caller caller = new CommandCaller(new OpenCommand());
        caller.call();

        caller.setCommand(new CreateCommand());
        caller.call();

        caller.setCommand(new EditCommand());
        caller.call();
    }
}
