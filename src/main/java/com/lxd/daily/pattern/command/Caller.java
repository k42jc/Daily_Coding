package com.lxd.daily.pattern.command;

import com.lxd.daily.pattern.command.command.Command;

/**
 * 命令调用抽象接口
 * Created by liaoxudong
 * Date:2018/7/20
 */

public interface Caller {

    void call();

    void setCommand(Command command);
}
