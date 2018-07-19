package com.lxd.daily.pattern.factory.abstra.demo;

import com.lxd.daily.pattern.factory.abstra.demo.face.AndoridFaceController;
import com.lxd.daily.pattern.factory.abstra.demo.face.FaceController;
import com.lxd.daily.pattern.factory.abstra.demo.operate.AndoridOpController;
import com.lxd.daily.pattern.factory.abstra.demo.operate.OperationController;

/**
 * Created by liaoxudong
 * Date:2018/7/17
 */

public class AndoridControllerFactory implements ControllerFactory{
    @Override
    public OperationController createOperateController() {
        return new AndoridOpController();
    }

    @Override
    public FaceController createFaceController() {
        return new AndoridFaceController();
    }
}
