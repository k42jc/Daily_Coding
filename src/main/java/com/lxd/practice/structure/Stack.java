package com.lxd.practice.structure;

/**
 * 栈:后进先出 LIFO ,提供压栈、弹出等操作
 *
 * 使用数组实现，提供基本操作
 * 计算逆波兰表达式
 * Created by liaoxudong on 2017/8/22.
 */
public class Stack {
    private Object[] values = new Object[1<<4];
    private int size = 0;
    private int writeIndex = 0;

    /**
     * 入栈
     * @param o
     */
    public void push(Object o) {
        if (size == values.length) {// 栈满
            throw new IndexOutOfBoundsException("栈已满");
        }
        values[writeIndex++] = o;
        size++;
    }

    /**
     * 出栈
     * @return
     */
    public Object pop() {
        if (size == 0) {
            return null;
        }
        Object result = values[size-1];
        values[size-1] = null;
        writeIndex--;
        size--;
        return result;
    }

    public int size(){
        return size;
    }

    /**
     * 获取最顶端元素
     * @return
     */
    public Object top(){
        return values[size-1];
    }

    /**
     * 计算 数字入栈，计算符号出栈两个元素并计算
     * @param o
     * @return
     */
    public void calc(Object o) {
        Object o1;
        Object o2;
        switch (o.toString()) {
            case "+":
                o1 = pop();
                o2 = pop();
                push(Integer.valueOf(o1.toString())+Integer.valueOf(o2.toString()));
                break;
            case "-":
                o1 = pop();
                o2 = pop();
                push(Integer.valueOf(o2.toString())-Integer.valueOf(o1.toString()));
                break;
            case "x":
                o1 = pop();
                o2 = pop();
                push(Integer.valueOf(o1.toString())*Integer.valueOf(o2.toString()));
                break;
            case "/":
                o1 = pop();
                o2 = pop();
                push(Integer.valueOf(o2.toString())/Integer.valueOf(o1.toString()));
                break;
            default:
                    push(o);
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        /*stack.push("1");
        System.out.println(stack.size());
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.size());*/

        //计算逆波兰表达式

        Object[] arrays = {12,3,4,"+","x",6,"-",8,2,"/","+"};
        for (Object o : arrays) {
            stack.calc(o);
        }
        // 最后计算获得值为82 -- > 12x(3+4)-6+8/2
        System.out.println(stack.pop());

    }
}
