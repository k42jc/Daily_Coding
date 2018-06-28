package com.lxd.daily.lettcode;

/**
 * Created by liaoxudong
 * Date:2018/6/27
 */

public class Q203 {
    private static class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode removeElements(ListNode head, int val) {
        if(head == null || (head.next == null && head.val == val)){
            return null;
        }
        ListNode node = head;
        // 遍历当前链表
        while(node.next != null){
            if(node.val == val){// 节点值与指定值不相等时，添加到结果链表
                ListNode next = node.next;
                node.val = next.val;
                node.next = next.next;
            }else{
                node = node.next;
            }

        }
        // 处理最后一个节点
        if(node.val == val){
            ListNode current = head;

            ListNode next = current.next; //如果next为空，那么就只有一个first存在，所以直接first为空就可以了
            if (next == null)
                head = null;
            else
                while (next.next != null){
                    current = next;
                    next = next.next;
                }

            current.next = null; // 这里为什么不能够直接写 next = null;
// 因为next = null 并不意味着 current 访问不到 current.next,还是可以的，
// 所以要写为 current.next = null, 这样才能保证删除最后一个节点
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        ListNode listNode1 = new Q203().removeElements(listNode, 1);
        System.out.println(listNode1);

    }
}
