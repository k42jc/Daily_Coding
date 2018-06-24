package com.lxd.daily.arithmetic.linked;

/**
 * 单链表
 */
public class Node {
    private int value;// 当前链表值
    private Node next;// 下一节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {// 重写toString 打印为value-next.value-next.value...
        StringBuilder builder = new StringBuilder(this.value+"");
        Node first = this;
        while (first.next != null) {
            first = first.next;
            builder.append("-").append(first.value);
        }
        return builder.toString();
    }

    /**
     * 将值添加到尾节点
     * @param value
     */
    void addLast(int value) {
        Node first = this;
        while (first.next != null) {
            first = first.next;
        }
        first.next = new Node(value);
    }
}
