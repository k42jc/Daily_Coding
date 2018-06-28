package com.lxd.daily.lettcode;

import java.util.Collections;

public class Q98 {
    private  static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        return isValid(root,root.val,0);
    }



    public boolean isValid(TreeNode root,int parentVal,int leftOrRight){
        boolean left = true;
        if(root.left != null){
            if(leftOrRight == 2 && root.left.val <= parentVal){
                return false;
            }
            if(root.left.val >= root.val){
                return false;
            }
            left = isValid(root.left,root.val,leftOrRight == 0?1:leftOrRight);
        }
        boolean right = true;
        if(root.right != null){
            if(leftOrRight == 1 && root.right.val >= parentVal){
                return false;
            }
            if(root.right.val <= root.val){
                return false;
            }

            right = isValid(root.right,root.val,leftOrRight == 0?2:leftOrRight);
        }

        return left == right && left;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(30);
        root.right.left = new TreeNode(10);
        root.right.left.right = new TreeNode(15);
        root.right.left.right.right = new TreeNode(45);
        boolean validBST = new Q98().isValidBST(root);
        System.out.println(validBST);
        int[] ints = new int[12];
        ints[2] = 0;
        for(int index=0;index<6;index+=2) {

        }
    }
}
