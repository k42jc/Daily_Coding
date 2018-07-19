package com.lxd.daily.pattern.composite.container;

import com.lxd.daily.pattern.composite.Controls;

import java.util.ArrayList;
import java.util.List;

/**
 * 中间面板容器
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class PanelContainer extends Container {
    // 面板内的组件 可以是具体组件也可以是容器
    private List<Controls> controlsList = new ArrayList<>();

    @Override
    public void addControls(Controls controls) {
        controlsList.add(controls);
    }

    @Override
    public void removeControls(int index) {
        controlsList.remove(index);
    }

    @Override
    public Controls getControls(int index) {
        return controlsList.get(index);
    }

    @Override
    public void display() {
        System.out.println("面板容器显示");
        for (Controls control : controlsList) {
            control.display();
        }
    }
}
