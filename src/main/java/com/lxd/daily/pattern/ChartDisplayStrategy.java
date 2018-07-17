package com.lxd.daily.pattern;

/**
 * 图标显示策略
 * Created by liaoxudong
 * Date:2018/7/16
 */

public class ChartDisplayStrategy {

    private ChartDisplay chartDisplay;

    /**
     * 具体图表显示由客户端指定
     * @param chartDisplay
     */
    public ChartDisplayStrategy(ChartDisplay chartDisplay) {
        this.chartDisplay = chartDisplay;
    }

    public void display(){
        this.chartDisplay.display();
    }
}
