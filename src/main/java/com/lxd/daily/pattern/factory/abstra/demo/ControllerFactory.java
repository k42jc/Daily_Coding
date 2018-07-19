package com.lxd.daily.pattern.factory.abstra.demo;

import com.lxd.daily.pattern.factory.abstra.demo.face.FaceController;
import com.lxd.daily.pattern.factory.abstra.demo.operate.OperationController;

/**
 * 控制器抽象工厂
 * Created by liaoxudong
 * Date:2018/7/17
 */

public interface ControllerFactory {

    /**
     * 创建操作控制器
     * @return
     */
    OperationController createOperateController();


    /**
     * 创建界面显示器
     * @return
     */
    FaceController createFaceController();
}
