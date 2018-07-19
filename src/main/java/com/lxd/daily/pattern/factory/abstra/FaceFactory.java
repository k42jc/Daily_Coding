package com.lxd.daily.pattern.factory.abstra;

import com.lxd.daily.pattern.factory.abstra.button.Button;
import com.lxd.daily.pattern.factory.abstra.comboBox.ComboBox;
import com.lxd.daily.pattern.factory.abstra.textField.TextField;

/**
 * 抽象界面组合接口
 * Created by liaoxudong
 * Date:2018/7/17
 */

public interface FaceFactory {

    /**
     * 创建按钮
     */
    Button createButton();

    /**
     * 创建输入框
     */
    TextField createTextFiled();


    /**
     * 创建单选框
     */
    ComboBox createComboBox();
}
