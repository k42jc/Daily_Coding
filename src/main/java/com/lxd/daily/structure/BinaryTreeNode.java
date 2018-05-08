package com.lxd.daily.structure;

/**
 * 二叉树
 * Created by liaoxudong on 2017/8/22.
 */
public class BinaryTreeNode {
    private String value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private boolean isRoot = false;

    public boolean hasLeft(){
        return left != null;
    }

    public boolean hasRight(){
        return right != null;
    }

    public boolean hasChild(){
        return left != null || right != null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
