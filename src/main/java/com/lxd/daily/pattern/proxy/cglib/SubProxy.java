package com.lxd.daily.pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB实现动态代理
 *
 * 不要求目标类一定实现接口
 *
 * 具体实现为：为目标类动态创建一个子类，调用子类目标方法过程调用父类方法
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class SubProxy implements MethodInterceptor{

    private Enhancer enhancer = new Enhancer();

    /**
     * 获取动态生成的代理子类
     * @param target
     * @return
     */
    public Object getProxy(Class target){
        enhancer.setSuperclass(target);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前操作...");
        methodProxy.invokeSuper(o, objects);
        System.out.println("执行后操作");
        return null;
    }
}
