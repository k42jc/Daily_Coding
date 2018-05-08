package com.lxd.daily.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 树
 * Created by liaoxudong on 2017/8/22.
 */
public class Node {
    private String value;// 节点数据
    private boolean isRoot;// 是否根节点
    private List<Node> children = new ArrayList<>();//子节点

    public boolean hashChild(){
        return children.size() != 0;
    }

    public void addNode(Node node){
        children.add(node);
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
