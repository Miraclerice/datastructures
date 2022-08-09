package com.itxie.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表测试：");
        //创建节点
        Node hero1 = new Node(1, "宋江", "及时雨");
        Node hero2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero3 = new Node(3, "吴用", "智多星");
        Node hero4 = new Node(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        Node newNode = new Node(4,"公孙胜","入云龙");
        doubleLinkedList.update(newNode);
        System.out.println("修改后：");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后：");
        doubleLinkedList.list();*/

        //按照编号添加
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();
    }
}


//创建一个双向链表类
class DoubleLinkedList {

    private Node head = new Node(0, "", "");

    public Node getHead() {
        return head;
    }

    //显示双向链表【遍历】
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        Node temp = head.next;
        while (true) {
            //判断链表是否最后
            if (temp == null) {
                break;
            }
//            输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表
    public void add(Node node) {
        //因为head节点不动，因此我们需要一个辅助对象遍历temp
        Node temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环，temp就指向链表最后
        //形成双向链表
        temp.next = node;
        node.pre = temp;
    }

    //按照标号顺序添加
    public void addByOrder(Node node) {
        //因为头结点不能动，因此我们需要辅助变量来遍历找到指定位置
        //因为单向链表，我名3找到的temp是位于添加位置的前一个节点，否则插入不了
        Node temp = head;
        boolean flag = false;//标识添加编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//temp为链表最后
                break;
            }
            if (temp.next.no > node.no) {//位置找到，就在temp后面插入
                break;
            } else if (temp.next.no == node.no) {//希望添加的heroNode编号已经存在
                flag = true;//编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag
        if (flag) {
            System.out.printf("准备插入的英雄编号%d 已经存在了，不能加入\n", node.no);
        } else {
            //擦插入到链表中
            node.next = temp.next;
            node.pre = temp;
            if(temp.next != null){
                temp.next.pre = node;
            }
            temp.next = node;
        }
    }

    //修改节点信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newNode的no来修改即可
    public void update(Node newNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义辅助变量
        Node temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else {
            System.out.printf("没有找到编号%d 的节点，不能修改\n", newNode.no);
        }
    }

    //删除节点
    //直接删除
    public void del(int no) {
        //判断是否为空
        if (head.next == null) {//空链表
            System.out.println("链表为空，不能删除~~");
            return;
        }
        Node temp = head.next;
        boolean flag = false;//标志是否找到删除节点
        while (true) {
            if (temp == null) {//链表最后
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//后移遍历
        }
        if (flag) {
            temp.pre.next = temp.next;
            //判断是否最后一个节点，空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("编号为%d 的节点不存在\n", no);
        }
    }

}

//定义Node，每个Node对象就是一个节点
class Node {
    public int no;
    public String name;
    public String nickName;
    public Node next;//指向下一个节点，默认为null
    public Node pre;//指向前一个节点，默认为null

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '\'' + '}';
    }
}