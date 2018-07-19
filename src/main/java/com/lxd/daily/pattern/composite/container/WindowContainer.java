package com.lxd.daily.pattern.composite.container;

import com.lxd.daily.pattern.composite.Controls;

import java.util.ArrayList;
import java.util.List;

/**
 * 窗口容器
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class WindowContainer extends Container {

    /**
     * 窗口内的组件，可能是容器也可能是具体组件
     */
    private List<Controls> controls = new ArrayList<>();

    @Override
    public void addControls(Controls control) {
        controls.add(control);
    }

    @Override
    public     void removeControls(int index) {
        controls.remove(index);
    }

    @Override
    public Controls getControls(int index) {
        return controls.get(index);
    }

    @Override
    public void display() {
        System.out.println("窗口容器显示");
        for (Controls control : controls) {
            control.display();
        }
    }
}
