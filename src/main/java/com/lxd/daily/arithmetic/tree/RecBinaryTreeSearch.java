package com.lxd.daily.arithmetic.tree;

import java.util.LinkedList;

/**
 * 递归二叉树遍历
 */
public class RecBinaryTreeSearch {

    /**
     *              1
     *             / \
     *            2   7
     *           / \  /\
     *          3  6 8  9
     *         / \
     *        4  5
     *
     * @return
     */
    public static Node buildTree(){
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);

        root.setLeft(two);
        root.setRight(seven);
        two.setLeft(three);
        two.setRight(six);
        three.setLeft(four);
        three.setRight(five);
        seven.setLeft(eight);
        seven.setRight(nine);
        return root;
    }

    /**
     * 递归前序遍历
     * @param tree
     */
    public static void preSearch(Node tree){
        System.out.println(tree.getValue());
        if (tree.hasLeft()) {
            preSearch(tree.getLeft());
        }
        if (tree.hasRight()) {
            preSearch(tree.getRight());
        }
    }

    /**
     * 递归前序遍历
     * @param tree
     */
    public static void middleSearch(Node tree){

            if (tree.hasLeft()) {
                middleSearch(tree.getLeft());
            }
        System.out.println(tree.getValue());
            if (tree.hasRight()) {
                middleSearch(tree.getRight());
            }

    }

    /**
     * 后序遍历
     * @param tree
     */
    public static void postSearch(Node tree) {
        if (tree.hasLeft()) {
            postSearch(tree.getLeft());
        }
        if (tree.hasRight()) {
            postSearch(tree.getRight());
        }
        System.out.println(tree.getValue());
    }

    private static final LinkedList<Node> stack = new LinkedList<>();

    /**
     * 压栈前序遍历
     * @param tree
     */
    public static void stackPreSearch(Node tree){
        if (tree.hasLeft()) {
            stack.push(tree.getLeft());
        }
        if (tree.hasRight()){
            stack.push(tree.getRight());
        }
            System.out.println(stack.pop().getValue());
    }

    public static void main(String[] args) {
        preSearch(buildTree());
        System.out.println("==========");
        middleSearch(buildTree());
        System.out.println("==========");
        postSearch(buildTree());
    }

}
