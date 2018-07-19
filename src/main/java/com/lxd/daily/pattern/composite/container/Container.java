package com.lxd.daily.pattern.composite.container;

import com.lxd.daily.pattern.composite.Controls;

/**
 * 容器抽象类
 * Created by liaoxudong
 * Date:2018/7/19
 */

public abstract class Container implements Controls{

    /**
     * 为容器添加具体组件
     */
    public abstract void addControls(Controls controls);

    public abstract void removeControls(int index);

    public abstract Controls getControls(int index);


}
