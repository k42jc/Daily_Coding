package com.lxd.daily.pattern.meditor;

/**
 * 具体中介者
 *
 * 维持组件类的引用，根据不同的组件做出不同的交互动作
 */
public class ConrectMediator extends Meditor{
    private Button button;
    private List list;
    private ComboBox comboBox;
    private TextBox textBox;

    @Override
    public void componentChanged(Component component) {
        // 单击按钮
        if (component == button) {
            System.out.println("--单击增加按钮");
            list.update();
            comboBox.update();
            textBox.update();
        } else if (component == list) {// 从列表中选择客户
            System.out.println("--从列表中选择客户");
            comboBox.select();
            textBox.show();
        } else if (component == comboBox) {// 从组合框中选择客户
            comboBox.select();
            textBox.show();
        }
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public void setTextBox(TextBox textBox) {
        this.textBox = textBox;
    }
}
