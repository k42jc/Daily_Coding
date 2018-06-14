package com.lxd.daily.lettcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定单链表  翻转指定区间的数据
 * 如：给定【1,2,3,4,5】 m=2 n=3
 * 得到结果：【1,4,3,2,5】
 * Created by liaoxudong
 * Date:2018/6/11
 */

public class Q92 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || (m >= n)){
            return head;
        }
        return reverse(head,m,n);
    }

    public ListNode reverse(ListNode head,int m,int n){
        ListNode first = head;
        int index =1;
        // 创建三个arrayList用于保存链表前中后三段
        List<Integer> pre = new ArrayList<>();
        List<Integer> middle = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        if(m == 1){
            middle.add(first.val);
        }else{
            pre.add(first.val);
        }
        while(head.next != null){
            first = head.next;
            int currentIndex = ++index;
            if(currentIndex < m){// 前
                pre.add(first.val);
            }else if(currentIndex > n){// 后
                post.add(first.val);
            }else{// 中
                middle.add(first.val);
            }
            head = head.next;
        }
        // 将元素添加到链表
        head = addNode(null,pre);
        head = addNode(head,reverseList(middle));
        return addNode(head,post);
    }

    public List<Integer> reverseList(List<Integer> list){
        List<Integer> resultList = new ArrayList<>();
        for(int i=list.size()-1;i>=0;i--){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    // 链表添加节点操作
    public ListNode addNode(ListNode head,List<Integer> list){
        for(int i=0;i<list.size();i++){
            if(head == null){
                head = new ListNode(list.get(i));
            }else{
                getLast(head).next = new ListNode(list.get(i));
            }
        }
        return head;

    }

    private ListNode getLast(ListNode node) {
        if(node == null) return null;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    // 节点添加
    public void addNode(ListNode head,ListNode next){
        ListNode first = head;
        while(first.next != null){
            first = first.next;
        }
        first.next = next;
    }

    public static void main(String[] args) {
//        ListNode listNode1 = new Q92().new ListNode(1);
//        ListNode listNode2 = new Q92().new ListNode(2);
        ListNode listNode3 = new Q92().new ListNode(3);
//        ListNode listNode4 = new Q92().new ListNode(4);
        ListNode listNode5 = new Q92().new ListNode(5);
        Q92 q92 = new Q92();
//        q92.addNode(listNode1,listNode2);
//        q92.addNode(listNode1,listNode3);
//        q92.addNode(listNode1,listNode4);
//        q92.addNode(listNode1,listNode5);
        q92.addNode(listNode3,listNode5);
        ListNode reverse = q92.reverseBetween(listNode3, 1, 2);
        System.out.println(reverse);
    }
}
