package com.itxie.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(105);
        circleSingleLinkedList.showBoy();
        //小孩出圈
        circleSingleLinkedList.countBoy(1,10,105);
    }
}

//创建单向环形链表
class CircleSingleLinkedList {
    //创建first节点，当前没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点，构建成环形链表
    public void addBoy(int nnums) {
        //nums数据校验
        if (nnums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针
        for (int i = 1; i <= nnums; i++) {
            Boy boy = new Boy(i);
            //如果第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;//指向下一个即boy
            }
        }
    }

    //遍历链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩！！！");
            return;
        }
        //first不能动，借助辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//最后一个小孩
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     * 根据用户输入，计算出出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多小小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助指针，帮助小孩出圈
        Boy helper = first;
        //辅助指针指向环形链表最后这个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first与helper同时移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，先让first与helper同时移动m-1次，然后出圈
        while (true) {
            if (helper == first) {//只有一个节点圈中
                break;
            }
            //先让first与helper同时移动countNum-1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //first指向节点，就是小孩出圈节点
            System.out.printf("小孩%d出圈淘汰\n", first.getNo());
            //first指向节点小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中小孩%d\n", helper.getNo());
    }
}

class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}