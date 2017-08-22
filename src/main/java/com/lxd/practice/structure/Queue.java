package com.lxd.practice.structure;

/**
 * 队列：先进先出，FIFO，提供enqueue、dequeue等操作
 * 本例使用数组实现
 *
 * Created by liaoxudong on 2017/8/22.
 */
public class Queue {

    private Object[] arrays = new Object[1 << 4];
    private int size = 0;
    private int writeIndex = 0;

    /**
     * 入队操作
     * @param o
     */
    public void enqueue(Object o) {
        arrays[writeIndex++] = o;
        size++;
    }

    /**
     * 出队
     * @return
     */
    public Object dequeue() {
        Object o = arrays[0];
        Object[] objects = new Object[16];
        if(size >1)
            System.arraycopy(arrays,1,objects,0,size-1);
        arrays[0] = null;
        arrays = objects;
        size--;
        writeIndex--;
        return o;
    }

    public int size(){
        return size;
    }


    public static void main(String[] args) {
        Queue queue = new Queue();
        /*queue.enqueue("1");
        System.out.println(queue.size());
        queue.dequeue();
        System.out.println(queue.size());*/

        // 使用队列进行树的广度优先遍历 ：优先遍历靠近根节点的各个节点


        Node root = new Node();
        root.setValue("A");
        root.setRoot(true);

        Node B = new Node();
        B.setValue("B");
        Node C = new Node();
        C.setValue("C");
        Node D = new Node();
        D.setValue("D");
        root.addNode(B);
        root.addNode(C);
        root.addNode(D);

        Node E = new Node();
        E.setValue("E");
        Node F = new Node();
        F.setValue("F");
        B.addNode(E);
        B.addNode(F);
        Node G = new Node();
        G.setValue("G");
        Node H = new Node();
        H.setValue("H");
        Node I = new Node();
        I.setValue("I");
        Node J = new Node();
        J.setValue("J");
        Node K = new Node();
        K.setValue("K");
        Node L = new Node();
        L.setValue("L");
        D.addNode(G);
        D.addNode(H);
        D.addNode(I);
        H.addNode(L);
        F.addNode(J);
        F.addNode(K);
/**
 *                  A
 *                / | \
 *               B  C  D
 *              /\     /|\
 *             E F    G H I
 *              /\      |
 *             J  K     L
 *
 *             广度优先遍历：从A B C D E F G H I J K L
 */
        // 根节点先入队，当队列中有元素时，继续遍历
        // 先出队，接下来从左侧根节点开始入队，再循环出队，即可完成目标操作
        queue.enqueue(root);
        while (queue.size() > 0){
            Node node =(Node) queue.dequeue();
            System.out.print(node.getValue()+" ");
            for(Node n:node.getChildren())
                queue.enqueue(n);
        }
        // 输出结果 ：A B C D E F G H I J K L


    }


}
