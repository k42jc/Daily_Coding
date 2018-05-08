package com.lxd.daily.thread.threadLocal;

/**
 * ThreadLocal会将变量副本绑定到当前线程，起到线程隔离的作用
 *
 * 主要应用在单线程多实例的场景下，如http服务器、动态数据源等
 *
 *
 * InheritableThreadLocal可以做到获取父级线程绑定的值
 * Created by liaoxudong
 * Date:2018/5/7
 */

public class ThreadLocalHolder {
    public static final ThreadLocalExt threadLocal = new ThreadLocalExt();
    public static final InheritableThreadLocalExt inheritableThreadLocalExt = new InheritableThreadLocalExt();

    public static void setValue(Object value) {
        threadLocal.set(value);
    }

    public static Object get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }

    public static void setInheritableValue(Object value) {
        inheritableThreadLocalExt.set(value);
    }

    public static Object getInheritable(){
        return inheritableThreadLocalExt.get();
    }

    public static void removeInheritable(){
        inheritableThreadLocalExt.remove();
    }

    static class ThreadLocalExt extends ThreadLocal{
        @Override
        protected Object initialValue() {
            return "init value";
        }
    }

    static class InheritableThreadLocalExt extends InheritableThreadLocal{
        @Override
        protected Object initialValue() {
            return "inheritable init value";
        }
    }
}
