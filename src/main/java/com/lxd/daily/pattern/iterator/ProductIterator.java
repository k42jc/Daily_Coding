package com.lxd.daily.pattern.iterator;

import java.util.List;

/**
 * 商品迭代器：具体迭代器，这里只负责商品集合的迭代遍历操作
 * @author liaoxudong
 * @date 2018/8/1
 */

public class ProductIterator implements AbstractIterator {
    private ProductList productList;
    private List<Object> products;
    /**
     * 顺序遍历游标
     */
    private int seqCursor;
    /**
     * 逆序遍历游标
     */
    private int revCursor;
    public ProductIterator(ProductList productList) {
        this.productList = productList;
        this.products = productList.getObjects();
        this.seqCursor = 0;
        this.revCursor = products.size()-1;
    }

    @Override
    public void next() {
        if (seqCursor >= products.size()) {
            throw new IllegalStateException("正向遍历完成");
        }
        seqCursor++;
    }

    @Override
    public void previous() {
        if(revCursor < 0){
            throw new IllegalStateException("元素已遍历完成");
        }
        revCursor--;
    }

    @Override
    public boolean isFirst() {
        return revCursor == -1;
    }

    @Override
    public boolean isLast() {
        return seqCursor == products.size();
    }

    @Override
    public Object getNextItem() {
        return products.get(seqCursor);
    }

    @Override
    public Object getPreviousItem() {
        return products.get(revCursor);
    }
}
