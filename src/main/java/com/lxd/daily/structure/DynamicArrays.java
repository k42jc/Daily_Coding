package com.lxd.daily.structure;

/**
 * 动态数组
 * ArrayList的实现原理与下面代码一致
 *
 * 维护一个动态数组，数据的存储是线性有序的，一直存储在最后一个位置
 * 当容量满时，扩容一倍(仅用于演示)
 *
 * Created by liaoxudong on 2017/8/22.
 */
public class DynamicArrays {
    // 默认16
    private Object[] arrays = new Object[1 << 4];

    /**
     * 添加到数组最后一个元素
     * @param o
     */
    public void add(Object o) {
        for(int i=0;i<arrays.length;i++) {
            if (arrays[i] == null && i < arrays.length-1) {
                setValue(i,o);
                return;
            }
            // 容量不够，自动扩容，
            else if(i == arrays.length-1){
                resize();
                add(o);
                return;
            }
        }
    }

    /**
     * 获取数组元素
     * @return 数组元素长度
     */
    public int size(){
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 返回数组长度
     * @return 数组长度
     */
    public int arrayLength(){
        return arrays.length;
    }

    /**
     * 默认扩容一倍
     */
    private void resize() {
        Object[] objects = new Object[arrays.length * 2];
        System.arraycopy(arrays,0,objects,0,arrays.length);
        arrays = objects;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for(int i=0;i<arrays.length-1;i++) {
            if (arrays[i] != null) {
                stringBuilder.append(arrays[i] + ",");
            }
        }
        return stringBuilder.append("]").toString();
    }

    /**
     * 数组设值
     * @param i 数组下标
     * @param o 值
     */
    private void setValue(int i, Object o) {
        if (i > arrays.length) {
            throw new IndexOutOfBoundsException(""+i);
        }
        arrays[i] = o;
    }

    public static void main(String[] args) {
        DynamicArrays dynamicArrays = new DynamicArrays();
//        dynamicArrays.add("1");
        System.out.println(dynamicArrays.size());
        System.out.println(dynamicArrays.arrayLength());
        for(int i=0;i<16;i++) {
            dynamicArrays.add(i);
        }
        System.out.println(dynamicArrays.size());
        System.out.println(dynamicArrays.arrayLength());
    }
}
