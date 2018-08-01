package com.lxd.daily.pattern.iterator;

/**
 * 抽象迭代器
 * @author liaoxudong
 * @date 2018/8/1
 */

public interface AbstractIterator {
    /**
     * 移动到下一个元素
     */
    void next();

    /**
     * 移动到上一个元素
     */
    void previous();

    /**
     * 是否是第一个元素
     * @return 是/否
     */
    boolean isFirst();
    /**
     * 是否为最后一个元素
     * @return 是/否
     */
    boolean isLast();

    /**
     * 获取下一个元素
     * @return 下一个元素
     */
    Object getNextItem();

    /**
     * 获取上一个元素
     * @return 上一个元素
     */
    Object getPreviousItem();


}
