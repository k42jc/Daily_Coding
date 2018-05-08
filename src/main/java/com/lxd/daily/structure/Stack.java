package com.lxd.daily.structure;

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

    /*public static void main(String[] args) {
        Stack stack = new Stack();
        *//*stack.push("1");
        System.out.println(stack.size());
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.size());*//*

        //计算逆波兰表达式

        Object[] arrays = {12,3,4,"+","x",6,"-",8,2,"/","+"};
        for (Object o : arrays) {
            stack.calc(o);
        }
        // 最后计算获得值为82 -- > 12x(3+4)-6+8/2
        System.out.println(stack.pop());

    }*/

    public static void main(String[] args) {
        BinaryTreeNode A = new BinaryTreeNode();
        A.setValue("A");
        A.setRoot(true);

        BinaryTreeNode B = new BinaryTreeNode();
        B.setValue("B");
        BinaryTreeNode C = new BinaryTreeNode();
        C.setValue("C");
        A.setLeft(B);
        A.setRight(C);

        BinaryTreeNode D = new BinaryTreeNode();
        D.setValue("D");
        BinaryTreeNode E = new BinaryTreeNode();
        E.setValue("E");
        BinaryTreeNode F = new BinaryTreeNode();
        F.setValue("F");
        BinaryTreeNode G = new BinaryTreeNode();
        G.setValue("G");
        B.setLeft(D);
        B.setRight(E);
        C.setLeft(F);
        C.setRight(G);

        BinaryTreeNode H = new BinaryTreeNode();
        H.setValue("H");
        BinaryTreeNode I = new BinaryTreeNode();
        I.setValue("I");
        D.setLeft(H);
        D.setRight(I);
        BinaryTreeNode J = new BinaryTreeNode();
        J.setValue("J");
        BinaryTreeNode K = new BinaryTreeNode();
        K.setValue("K");
        BinaryTreeNode L = new BinaryTreeNode();
        L.setValue("L");
        BinaryTreeNode M = new BinaryTreeNode();
        M.setValue("M");
        BinaryTreeNode N = new BinaryTreeNode();
        N.setValue("N");
        I.setLeft(M);
        I.setRight(N);
        F.setLeft(J);
        F.setRight(K);
        G.setLeft(L);

        /**
         *          A
         *       /     \
         *      B       C
         *     /  \     /\
         *    D    E   F  G
         *   / \     / \  /
         *  H   I   J  K L
         *     / \
         *    M   N
         */
        Stack stack = new Stack();
//        stack.treeErgodic1(A);
        // 输出：A B D H I M N E C F J K G L
//        stack.treeErgodic2(A);
        // 输出：H D M I N B E A J F K C L G
//        stack.treeErgodic3(A);
        // 输出：H M N I D E B J K F L G C A

//        stack.raverse(A);
//        stack.traverse1(A);// A B D H I M N E C F J K G L
//        stack.traverse2(A);//H D M I N B E A J F K C L G
        stack.traverse3(A);


    }

    /**
     * 树的递归遍历
     * @param node
     */
    public void raverse(BinaryTreeNode node) {
        if(node!= null){
            //放在最前面 前序遍历
            System.out.print(node.getValue()+" ");
//            System.out.print(node.getValue()+" ");
            raverse(node.getLeft());
            // 中间 中序遍历
//            System.out.print(node.getValue()+" ");
            raverse(node.getRight());
            // 最后 后序遍历
//            System.out.print(node.getValue()+" ");
        }
    }

    /**
     * 使用栈前序遍历树节点
     * @param node
     */
    public void traverse1(BinaryTreeNode node) {
        BinaryTreeNode pNode = node;
        while (pNode != null || size() > 0) {
            if (pNode != null) {
                System.out.print(pNode.getValue()+" ");
                push(pNode);
                pNode = pNode.getLeft();
            }else{
                pNode = ((BinaryTreeNode) pop()).getRight();
            }
        }
    }

    /**
     * 使用栈中序遍历树
     * @param node
     */
    public void traverse2(BinaryTreeNode node) {
        BinaryTreeNode pNode = node;
        while (pNode != null || size() > 0) {
            if (pNode != null) {
                push(pNode);
                pNode = pNode.getLeft();
            }else{
                BinaryTreeNode pop = (BinaryTreeNode) pop();
                System.out.print(pop.getValue()+" ");
                pNode = pop.getRight();
            }
        }
    }

    /**
     * 使用栈后序遍历树
     * @param node
     */
    public void traverse3(BinaryTreeNode node) {
        BinaryTreeNode pNode = node;
        while (pNode != null || size() > 0) {
            if (pNode != null) {
                push(pNode);
                BinaryTreeNode left = pNode.getLeft();
                pNode = left;
            }else{
                BinaryTreeNode pop = (BinaryTreeNode) pop();
                System.out.println(pop.getValue());
                pNode = pop;
            }
        }
    }




    /**
     * 树的深度遍历：前序遍历:::根节点->左子节点->右子节点
     */
    public void treeErgodic1(BinaryTreeNode node){
        if (node == null || !node.hasChild()) {
            return;
        }
        push(node);
        while (size()>0) {
            BinaryTreeNode pop = (BinaryTreeNode) pop();
            System.out.print(pop.getValue()+" ");
            if (pop.hasRight()) {
                push(pop.getRight());
            }
            if(pop.hasLeft()){
                push(pop.getLeft());
            }
        }
        // 从右节点至左依次压入栈，再依次弹出
    }
    //，后续遍历，中序遍历。其中，中序遍历只对二叉树有效

    /**
     * 二叉树中序遍历:左子节点->根节点->右子节点
     * @param node
     */
    public void treeErgodic2(BinaryTreeNode node){
        push(node);
        while (size() > 0) {
            BinaryTreeNode pop = (BinaryTreeNode) pop();
            if(!pop.hasChild() || !pop.hasLeft()){
                System.out.print(pop.getValue()+" ");
            }else{
                if(pop.hasRight()){
                   push(pop.getRight());
                }
                if(pop.hasLeft()){
                    BinaryTreeNode lNode = pop.getLeft();
                    // 关键操作 否则根节点遍历不到
                    pop.setLeft(null);
                    push(pop);
                    push(lNode);
                }
            }
        }
    }

    /**
     * 后序遍历：左子节点->右子节点->根节点
     * @param node
     */
    public void treeErgodic3(BinaryTreeNode node) {
        push(node);
        while (size() > 0) {
            BinaryTreeNode pop = (BinaryTreeNode) pop();
            if(!pop.hasChild()){
                System.out.print(pop.getValue()+" ");
            }else{
                BinaryTreeNode pNode = pop;
                push(pNode);
                if(pop.hasRight()){
                    push(pop.getRight());
                }
                if(pop.hasLeft()){
                    push(pop.getLeft());
                }
                // 关键操作 需要先将左右子节点入栈后再置空
                pNode.setLeft(null);
                pNode.setRight(null);
            }
        }
    }
}
