package com.lxd.daily.pattern.factory.abstra.demo;

import com.lxd.daily.pattern.factory.abstra.demo.face.FaceController;
import com.lxd.daily.pattern.factory.abstra.demo.face.IOSFaceController;
import com.lxd.daily.pattern.factory.abstra.demo.operate.IOSOpController;
import com.lxd.daily.pattern.factory.abstra.demo.operate.OperationController;

/**
 * Created by liaoxudong
 * Date:2018/7/17
 */

public class IOSControllerFactory implements ControllerFactory {
    @Override
    public OperationController createOperateController() {
        return new IOSOpController();
    }

    @Override
    public FaceController createFaceController() {
        return new IOSFaceController();
    }
}
