package com.lxd.daily.arithmetic.tree;

import java.util.LinkedList;

/**
 * 非递归二叉树遍历
 * 使用栈遍历
 */
public class StackBTreeSearch {

    private static final LinkedList<Node> stack = new LinkedList<>();

    public static void main(String[] args) {
        Node tree = RecBinaryTreeSearch.buildTree();
//        preList(tree);

//        middleList(tree);
            // 栈后续遍历比较复杂 算了 不研究茴字的五种写法了，使用递归就挺方便的，如果真要用到栈后续遍历，百度/谷歌即可
//        postList(tree);
    }

    /**
     * 使用栈前序遍历
     *
     * @param tree
     */
    private static void preList(Node tree) {
        while (tree != null) {
            while (tree != null) {
                System.out.println(tree.getValue());
                stack.push(tree);
                tree = tree.getLeft();
            }
            tree = stack.poll();//使用poll 无元素时弹出null 如果是pop则会在无元素时抛出NoSuchElementException
            // 这里比较关键 左子树出栈时需要判断是否子节点，如果是子节点则需要将父节点出栈
            // 另外还要考虑到右子树最后一个子节点时栈为空时不再弹出
            if (tree.isLeaf() && !stack.isEmpty())
                tree = stack.poll();
            tree = tree.getRight();

        }
    }

    /**
     * 栈中序遍历
     *
     * @param tree
     */
    private static void middleList(Node tree) {
        while (tree != null) {
            while (tree != null) {
                stack.push(tree);
                tree = tree.getLeft();
            }
            tree = stack.poll();
            System.out.println(tree.getValue());
            if (tree.isLeaf() && !stack.isEmpty()) {
                tree = stack.poll();
                System.out.println(tree.getValue());
            }
            tree = tree.getRight();
        }
    }

}
