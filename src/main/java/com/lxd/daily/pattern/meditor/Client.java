package com.lxd.daily.pattern.meditor;


/**
 * 中介者模式客户端调用示例
 */
public class Client {

    public static void main(String[] args){
        // 定义中介者
        ConrectMediator meditor = new ConrectMediator();

        // 定义同事对象
        Button button = new Button();
        List list = new List();
        ComboBox comboBox = new ComboBox();
        TextBox textBox = new TextBox();

        // 为同事对象添加中介者
        button.setMeditor(meditor);
        list.setMeditor(meditor);
        comboBox.setMeditor(meditor);
        textBox.setMeditor(meditor);

        // 设置具体中介者的组件对象
        meditor.setButton(button);
        meditor.setList(list);
        meditor.setComboBox(comboBox);
        meditor.setTextBox(textBox);

        // 执行事件调用
        button.changed();
        list.changed();
    }
}
