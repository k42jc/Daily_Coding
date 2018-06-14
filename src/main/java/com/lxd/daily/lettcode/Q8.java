package com.lxd.daily.lettcode;

/**
 * Created by liaoxudong
 * Date:2018/6/8
 */

public class Q8 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length){
            throw new IllegalArgumentException();
        }
//        return build(preorder,inorder);
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    /**
     * 从根节点开始，递归创建二叉树
     */
    private TreeNode build(int[] pres,int[] middiles){
        if (pres.length <= 0 || middiles.length <= 0) {
            return null;
        }
        // 每个子树的前序遍历第一个元素必定是根节点
        TreeNode root = new TreeNode(pres[0]);
        int index = indexOf(middiles, root.val);
        if (index == -1) {
            return root;
        }
        // 从中序遍历找到根节点index，则左边是左子节点的中序遍历结果，右边是右子节点中序遍历结果
        // 再从前序遍历中找到从根节点开始，长度=左子节点中序遍历结果长度内的值时左子节点的前序遍历结果，剩下的是右子节点的前序遍历结果
        int[] newLeftpres = new int[index];
        System.arraycopy(pres,1,newLeftpres,0,newLeftpres.length);
        int[] newLeftMiddles = new int[index];
        System.arraycopy(middiles,0,newLeftMiddles,0,newLeftMiddles.length);
        root.left = build(newLeftpres, newLeftMiddles);
        int[] newRightPres = new int[pres.length-index-1];
        System.arraycopy(pres,index+1,newRightPres,0,newRightPres.length);
        int[] newRightMiddles = new int[pres.length-index-1];
        System.arraycopy(middiles,index+1,newRightMiddles,0,newRightMiddles.length);
        root.right = build(newRightPres, newRightMiddles);
        return root;
    }

    // 针对上面必须要创建多个新数组方法的改良，使用移动指针
    private TreeNode build(int[] pres, int preStart, int preEnd, int[] middles, int middleStart, int middleEnd) {
        if (preStart > preEnd || middleStart>middleEnd) {
            return null;
        }
        // 每个子树的前序遍历第一个元素必定是根节点
        TreeNode root = new TreeNode(pres[preStart]);
        int index = indexOf(middles, root.val);
        if (index == -1) {
            return root;
        }
        root.left = build(pres,preStart+1,preStart+index, middles,middleStart,index-1);
        root.right = build(pres,index+1,preEnd,middles,index,middleEnd);
        return root;
    }

    private int indexOf(int[] nums,int num){
        if(nums == null || nums.length <= 0){
            return -1;
        }
        for(int index=0;index<nums.length;index++){
            if(nums[index] == num){
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] pres = {3,9,20,15,7};
        int[] middles = {9,3,15,20,7};
        TreeNode treeNode = new Q8().buildTree(pres, middles);
        System.out.println(treeNode);
    }
}
