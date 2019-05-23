package com.lxd.daily.pattern.meditor;

/**
 * 抽象组件类
 */
public abstract class Component {

    protected Meditor meditor;

    public void setMeditor(Meditor meditor) {
        this.meditor = meditor;
    }

    /**
     * 转发调用
     */
    public void changed(){
        meditor.componentChanged(this);
    }

    /**
     * 具体组件事件
     */
    public abstract void update();
}
