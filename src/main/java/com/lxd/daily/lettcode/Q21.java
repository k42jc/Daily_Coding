package com.lxd.daily.lettcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liaoxudong
 * Date:2018/6/13
 */

public class Q21 {

    private static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        // 先遍历获取两个链表的所有值放入一个有序数组
        List<Integer> vals = forListNode(l1,l2);
        // 将有序数组元素添加到链表
        return addNode(vals);
    }

    private ListNode addNode(List<Integer> vals){
        ListNode result = new ListNode(vals.get(0));
        for(int i=1;i<vals.size();i++){
            add(result, vals.get(i));
        }
        return result;
    }

    private void add(ListNode result, int val) {
        while(result.next != null){
            result = result.next;
        }
        result.next = new ListNode(val);
    }

    private List<Integer> forListNode(ListNode l1,ListNode l2){
        List<Integer> resultList = new ArrayList<>();
        resultList.add(l1.val);
        while(l1.next != null){
            l1 = l1.next;
            resultList.add(l1.val);
        }
        resultList.add(l2.val);
        while(l2.next != null){
            l2 = l2.next;
            resultList.add(l2.val);
        }
        Collections.sort(resultList);
        return resultList;
    }

    public static void main(String[] args) {
        ListNode one1 = new ListNode(1);
        ListNode one2 = new ListNode(2);
        ListNode one3 = new ListNode(3);
        one1.next = one2;
        one2.next = one3;
        ListNode one4 = new ListNode(1);
        ListNode one5 = new ListNode(3);
        ListNode one6 = new ListNode(4);
        one4.next = one5;
        one5.next = one6;
        ListNode listNode = new Q21().mergeTwoLists(one1, one4);
        System.out.println(listNode);
    }
}
