package com.lxd.daily.pattern.command;

import com.lxd.daily.pattern.command.command.Command;

/**
 * 命令调用者
 * Created by liaoxudong
 * Date:2018/7/20
 */

public class CommandCaller implements Caller{

    private Command command;

    public CommandCaller(Command command) {
        this.command = command;
    }

    public void call(){
        this.command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
