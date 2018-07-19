package com.lxd.daily.pattern.factory.abstra;

import com.lxd.daily.pattern.factory.abstra.button.Button;
import com.lxd.daily.pattern.factory.abstra.button.SpringButton;
import com.lxd.daily.pattern.factory.abstra.comboBox.ComboBox;
import com.lxd.daily.pattern.factory.abstra.comboBox.SpringComboBox;
import com.lxd.daily.pattern.factory.abstra.textField.SpringTextField;
import com.lxd.daily.pattern.factory.abstra.textField.TextField;

/**
 * 绿色界面组件工厂
 *
 * 可以生产绿色风格的一个完整产品族
 * Created by liaoxudong
 * Date:2018/7/17
 */

public class SpringFaceFactory implements FaceFactory{

    @Override
    public Button createButton() {
        return new SpringButton();
    }

    @Override
    public TextField createTextFiled() {
        return new SpringTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SpringComboBox();
    }
}
