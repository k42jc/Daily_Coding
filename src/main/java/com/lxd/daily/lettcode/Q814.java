package com.lxd.daily.lettcode;

import com.lxd.daily.lettcode.Q8.TreeNode;

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
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(0);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(1);
        TreeNode treeNode1 = new Q814().pruneTree(treeNode);
        System.out.println(treeNode1);
    }
}
