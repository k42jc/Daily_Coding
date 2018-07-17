package com.lxd.daily.lettcode;

import com.lxd.daily.lettcode.Q8.TreeNode;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by liaoxudong
 * Date:2018/6/28
 */

public class Q814 {

    public TreeNode pruneTree(TreeNode root) {
        if(root == null){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if(root.left == null && root.right == null && root.val == 0){
            return null;
        }
        return root;
    }


    public static void main(String[] args) {
        /*TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(0);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(1);
        new Q814().gdList(treeNode);
        TreeNode treeNode1 = new Q814().pruneTree(treeNode);
        System.out.println(treeNode1);*/
        System.out.println(new Random().nextInt(2));

    }

    /**
     * 广度遍历
     * @param node
     */
    private void gdList(TreeNode node) {
        LinkedList<TreeNode> linked = new LinkedList<>();
        linked.addLast(node);
        while (!linked.isEmpty()) {
            TreeNode root = linked.poll();
            if(root == null){
                continue;
            }
            System.out.println(root.val);
            linked.addLast(root.left);
            linked.addLast(root.right);
        }
    }
}
