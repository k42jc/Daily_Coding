package com.lxd.daily.pattern.iterator;

import java.util.List;

/**
 * 商品数据类：具体聚合类
 *
 * @author liaoxudong
 * @date 2018/8/1
 */

public class ProductList extends AbstractObjectList{

    public ProductList(List<Object> objects) {
        super(objects);
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
