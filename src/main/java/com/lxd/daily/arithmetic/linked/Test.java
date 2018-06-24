package com.lxd.daily.arithmetic.linked;

/**
 * 单链表的增删改
 */
public class Test {

    public static void main(String[] args){
        Node first = new Node(1);
        first.addLast(2);
        first.addLast(3);
        first.addLast(4);
        first.addLast(5);
        first.addLast(6);
        System.out.println(first.toString());
    }
}
