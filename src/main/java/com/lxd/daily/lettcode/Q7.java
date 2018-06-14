package com.lxd.daily.lettcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoxudong
 * Date:2018/6/5
 */

public class Q7 {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null){
            throw new IllegalArgumentException("inorder & postorder");
        }
        // 单个元素时
        if(inorder.length == 1 ||postorder.length == 1){
            return new TreeNode(inorder[0]);
        }
        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }
    private static TreeNode build(int[] inorder,int inStartPos,int inEndPos,int[] postorder,int postStartPos,int postEndPos){
        if(inStartPos > inEndPos){
            return null;
        }
        // 根据后序遍历找到根节点 从根节点开始 一个个创建
        TreeNode root = new TreeNode(postorder[postEndPos]);
        // 根据中序遍历从第一个根节点开始，找到根节点的左右子节点
        int rootPos = -1;
        for(int i=0;i<inorder.length;i++){
            if(inorder[i] == root.val){
                rootPos = i;
                break;
            }
        }
        // 以左子树为根节点 获取对应的中序和后序列表 然后递归创建节点
        root.left = build(inorder,inStartPos,rootPos-1,postorder,postStartPos,postStartPos+(rootPos - inStartPos -1));
        root.right = build(inorder,rootPos+1,inEndPos,postorder,postStartPos+(rootPos - inStartPos),postEndPos-1);
        return root;
    }

    public static void main(String[] args) {
        /*int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode treeNode = buildTree(inorder, postorder);
        System.out.println(treeNode);*/

        countSegments(", , , ,        a, eaefa");
    }

    public static int countSegments(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        String[] words = s.split(" ");
        // 过滤多余空格
        List<String> list = new ArrayList<String>(words.length);
        for(String word:words){
            if("".equals(word)){
                continue;
            }
            list.add(word);
        }
        return list.size();
    }


}
