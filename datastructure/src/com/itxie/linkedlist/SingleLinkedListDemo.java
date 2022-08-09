package com.itxie.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "林冲", "豹子头");
        HeroNode hero7 = new HeroNode(7, "林冲", "豹子头");
        HeroNode hero8 = new HeroNode(8, "林冲", "豹子头");

        //创建单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        SingleLinkedList singleLinkedList3 = new SingleLinkedList();


        //添加数据
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/

        //按照编号顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero6);
        singleLinkedList1.addByOrder(hero7);

        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero5);
        singleLinkedList2.addByOrder(hero8);

        //合并链表，仍有序
        //合并前链表
        System.out.println("合并前链表：");
        System.out.println("链表1~~~~~~~~~~~~~~~~~~~~~");
        singleLinkedList1.list();
        System.out.println();
        System.out.println("链表2~~~~~~~~~~~~~~~~~~~~~");
        singleLinkedList2.list();
        System.out.println();
        //合并后链表
        twoLinkedList(singleLinkedList1.getHead(),singleLinkedList2.getHead(),singleLinkedList3.getHead());
        System.out.println("合并链表后：");
        singleLinkedList3.list();

/*      //测试单链表反转功能
        System.out.println("原链表的情况：");
        singleLinkedList.list();*/
/*
        System.out.println("反转单链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();*/

/*        System.out.println("逆序打印单链表：");
        reversePrint(singleLinkedList.getHead());*/

        /*singleLinkedList.list();

        //测试修改节点代码
        HeroNode newHeroNode = new HeroNode(2, "卢义", "玉麒麟");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后：--------");

        singleLinkedList.list();


        //删除节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        //singleLinkedList.del(3);
        //singleLinkedList.del(2);
        System.out.println("删除后~~~");
        singleLinkedList.list();

        //求单链表中有效节点的个数
        System.out.println("" + getLength(singleLinkedList.getHead()));

        //测试是否得到了倒数第k个节点
        HeroNode res = finaLastIndexNode(singleLinkedList.getHead(), 3);
        System.out.println("res=" + res);*/

    }

    // twoLinkedList方法
    // 传入待合并的两个链表的头节点以及第三个单链表的头节点
    public static void twoLinkedList(HeroNode head1, HeroNode head2, HeroNode head3) {
        if (head1.next == null && head2.next == null) {
            return;
        }
        // 如果链表1为空，则将head3.next指向head2.next，实现链表2中的节点连接到链表3
        if (head1.next == null) {
            head3.next = head2.next;
        } else {
            head3.next = head1.next;// 将head3.next指向head1.next，实现链表1中的节点连接到链表3
            HeroNode cur2 = head2.next;
            HeroNode cur3 = head3;
            HeroNode next = null;
            // 遍历链表2，将其节点按顺序连接至链表3
            while(cur2 != null){
                // 链表3遍历完毕后，可以直接将链表2剩下的节点连接至链表3的末尾
                if(cur3.next == null){
                    cur3.next = cur2;
                    break;
                }
                // 在链表3中，找到第一个大于链表2中的节点编号的节点
                // 因为是单链表，找到的节点是位于添加位置的前一个节点，否则无法插入
                if(cur2.no <= cur3.next.no) {
                    next = cur2.next;  // 先暂时保存链表2中当前节点的下一个节点，方便后续使用
                    cur2.next = cur3.next;  // 将cur2的下一个节点指向cur3的下一个节点
                    cur3.next = cur2;  // 将cur2连接到链表3上
                    cur2 = next;  // 让cur2后移
                }
                // 遍历链表3
                cur3 = cur3.next;
            }
        }
    }

    //利用栈数据结构，将各个节点压入栈中，然后利用栈的先进后出特点，实现逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表不打印
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表所有结点压栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //弹栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //将单链表反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针（变量），帮助遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前结点【cur】的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next;//暂时保存下一节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新链表最前端
            reverseHead.next = cur;
            cur = next;//cur后移
        }
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第k个节点
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表总长度getLength
    //4.得到size后，我们从链表的第一个开始遍历（size-index）,就可以得到
    //5.如果找到了，则返回该节点，否则返回null
    public static HeroNode finaLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历size-index位置，就是我们倒数的第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量,for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //方法：获取单链表的节点个数（如果是带头节点的链表，需求不统计头结点）

    /**
     * @param head 链表的头节点
     * @return 返回就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加一个节点到单向链表
    //思路：不考虑序号时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不动，因此我们需要一个辅助对象遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环，temp就指向链表最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种方式添加英雄，根据排名将英雄插入指定位置
    //添加失败，给出提示
    public void addByOrder(HeroNode heroNode) {
        //因为头结点不能动，因此我们需要辅助变量来遍历找到指定位置
        //因为单向链表，我名3找到的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标识添加编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//temp为链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//希望添加的heroNode编号已经存在
                flag = true;//编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag
        if (flag) {
            System.out.printf("准备插入的英雄编号%d 已经存在了，不能加入\n", heroNode.no);
        } else {
            //擦插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号%d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点

    /**
     * 1.head不能动，因此需要一个temp辅助节点来找到待删除节点的前一个节点
     * 2.说明我们在比较时，是temp.next.no和需要删除节点的比较
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//标志是否找到删除节点
        while (true) {
            if (temp.next == null) {//链表最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//后移遍历
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("编号为%d 的节点不存在\n", no);
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
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

}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '\'' + '}';
    }
}