package com.itxie.stack;

public class SingleLinkedListStackDemo {
    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        SingleLinkedListStack stack = new SingleLinkedListStack();
        stack.push(node1);
        stack.push(node2);
        stack.push(node3);
        stack.push(node4);

        stack.show();

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.show();

        stack.pop();

    }
}


class SingleLinkedListStack {
    private Node top = new Node(-1);//栈顶，初始为-1

    //判断栈是否为空
    public boolean isEmpty() {
        return top.getNext() == null;
    }

    //入栈(头插法）
    public void push(Node node) {
        if (top.getNext() == null) {
            top.setNext(node);//第一个节点插入
            return;
        }
        Node temp = top.getNext();
        top.setNext(node);
        node.setNext(temp);
    }

    //出栈
    public void pop() {
        if (top.getNext() == null) {//没有数据
            top.setNo(-1);
            System.out.println("栈为空,无法弹出");
            return;
        }
        System.out.println("弹栈节点为：" + top.getNext().getNo());
        top = top.getNext();
    }

    //遍历显示
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        Node cur = top;
        while (cur.getNext() != null) {
            System.out.println("栈中节点为:" + cur.getNext().getNo());
            cur = cur.getNext();
        }
    }
}


//栈的链表节点类
class Node {
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}