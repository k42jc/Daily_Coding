package com.lxd.daily.pattern.composite;

import com.lxd.daily.pattern.composite.container.Container;
import com.lxd.daily.pattern.composite.container.PanelContainer;
import com.lxd.daily.pattern.composite.container.WindowContainer;

/**
 * 组合模式客户端调用示例
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class CompositeClient {

    public static void main(String[] args) {
        Container windowContainer = new WindowContainer();
        Controls button = new Button();
        button.display();
        Controls text = new Text();
        text.display();
        windowContainer.addControls(button);
        windowContainer.addControls(text);
        windowContainer.display();

        Container panelContainer = new PanelContainer();
        panelContainer.addControls(button);
        panelContainer.addControls(text);
        panelContainer.display();
    }
}
