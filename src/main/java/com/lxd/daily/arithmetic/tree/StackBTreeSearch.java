package com.lxd.daily.arithmetic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 非递归二叉树遍历
 * 使用栈迭代遍历
 */
public class StackBTreeSearch {

    private static final LinkedList<Node> stack = new LinkedList<>();

    public static void main(String[] args) {
        Node tree = RecBinaryTreeSearch.buildTree();
//        preList(tree);
        List<Integer> integers = new StackBTreeSearch().preList(tree);
        System.out.println(integers);
        List<Integer> integes = new StackBTreeSearch().middleList(tree);
        System.out.println(integes);
        List<Integer> integerss = new StackBTreeSearch().postList(tree);
        System.out.println(integerss);
    }

    /**
     * 迭代式前序遍历：操作根节点->访问左子节点->访问右子节点
     * @param tree
     * @return
     */
    public List<Integer> preList(Node tree){
        if (tree == null) {
            throw new IllegalArgumentException("tree");
        }
        LinkedList<TreeNodeOperate> stack = new LinkedList<>();
        // 将树结构入栈
        stack.push(new TreeNodeOperate(0,tree));
        // 使用栈对树结构迭代操作
        List<Integer> resultList = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNodeOperate nodeOperate = stack.poll();// 不使用pop 以免栈内无元素时抛出异常
            if (nodeOperate.node == null) {
                continue;
            }else{
                if (nodeOperate.type == 1) {
                    resultList.add(nodeOperate.node.getValue());
                }else{
                    // 实际顺序为：操作根节点->访问左子节点->访问右子节点
                    // 考虑到栈LIFO特性，反过来入栈
                    stack.push(new TreeNodeOperate(0,nodeOperate.node.getRight()));
                    stack.push(new TreeNodeOperate(0,nodeOperate.node.getLeft()));
                    stack.push(new TreeNodeOperate(1,nodeOperate.node));
                }
            }
        }
        return resultList;
    }

    /**
     * 迭代式前序遍历：操作根节点->访问左子节点->访问右子节点
     * @param tree
     * @return
     */
    public List<Integer> middleList(Node tree){
        if (tree == null) {
            throw new IllegalArgumentException("tree");
        }
        LinkedList<TreeNodeOperate> stack = new LinkedList<>();
        // 将树结构入栈
        stack.push(new TreeNodeOperate(0,tree));
        // 使用栈对树结构迭代操作
        List<Integer> resultList = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNodeOperate nodeOperate = stack.poll();// 不使用pop 以免栈内无元素时抛出异常
            if (nodeOperate.node == null) {
                continue;
            }else{
                if (nodeOperate.type == 1) {
                    resultList.add(nodeOperate.node.getValue());
                }else{
                    // 入栈顺序根据谦虚调整一下即可
                    // 前序实际顺序是：中-左-右
                    // 而中序实际顺序是：左-中-右，所以把入栈顺序调整为左子节点以操作类型最后入栈
                    stack.push(new TreeNodeOperate(0,nodeOperate.node.getRight()));
                    stack.push(new TreeNodeOperate(1,nodeOperate.node));
                    stack.push(new TreeNodeOperate(0,nodeOperate.node.getLeft()));
                }
            }
        }
        return resultList;
    }

    /**
     * 迭代式前序遍历：操作根节点->访问左子节点->访问右子节点
     * @param tree
     * @return
     */
    public List<Integer> postList(Node tree){
        if (tree == null) {
            throw new IllegalArgumentException("tree");
        }
        LinkedList<TreeNodeOperate> stack = new LinkedList<>();
        // 将树结构入栈
        stack.push(new TreeNodeOperate(0,tree));
        // 使用栈对树结构迭代操作
        List<Integer> resultList = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNodeOperate nodeOperate = stack.poll();// 不使用pop 以免栈内无元素时抛出异常
            if (nodeOperate.node == null) {
                continue;
            }else{
                if (nodeOperate.type == 1) {
                    resultList.add(nodeOperate.node.getValue());
                }else{
                    // 入栈顺序根据谦虚调整一下即可
                    // 前序实际顺序是：中-左-右
                    // 中序实际顺序是：左-中-右
                    // 后序：左-右-中
                    stack.push(new TreeNodeOperate(1,nodeOperate.node));
                    stack.push(new TreeNodeOperate(0,nodeOperate.node.getRight()));
                    stack.push(new TreeNodeOperate(0,nodeOperate.node.getLeft()));
                }
            }
        }
        return resultList;
    }

    /**
     * 对树节点对应操作的封装
     */
    private class TreeNodeOperate{
        int type = 0;// 节点操作类型 0 访问 1 操作
        Node node;// 对应树节点

        public TreeNodeOperate(int type, Node node) {
            this.type = type;
            this.node = node;
        }
    }


}
