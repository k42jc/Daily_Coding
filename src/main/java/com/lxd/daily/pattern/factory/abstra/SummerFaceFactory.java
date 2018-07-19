package com.lxd.daily.pattern.factory.abstra;

import com.lxd.daily.pattern.factory.abstra.button.Button;
import com.lxd.daily.pattern.factory.abstra.button.SummerButton;
import com.lxd.daily.pattern.factory.abstra.comboBox.ComboBox;
import com.lxd.daily.pattern.factory.abstra.comboBox.SummerComboBox;
import com.lxd.daily.pattern.factory.abstra.textField.SummerTextField;
import com.lxd.daily.pattern.factory.abstra.textField.TextField;

/**
 * Created by liaoxudong
 * Date:2018/7/17
 */

public class SummerFaceFactory implements FaceFactory{
    @Override
    public Button createButton() {
        return new SummerButton();
    }

    @Override
    public TextField createTextFiled() {
        return new SummerTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
