package com.atguigu.linkedlist;

import javafx.scene.shape.HLineTo;

/**
 * @Author jacklu
 * @Date 11:45:35 2021/03/21
 */
//约瑟夫问题的模拟解决方案
public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2);
    }
}


//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点, 当前没有编号, 初始化之后first节点永远指向第一个节点
    private Boy first = new Boy(-1);
    int nums;

    //添加小孩节点, 构建成一个环形链表(初始化)
    public void addBoy(int nums) {
        this.nums = nums;
        //做个校验, 添加的小孩数必须大于等于1
        if (nums < 1) {
            System.out.println("nums的值必须大于等于一");
            return;
        }
        Boy curBoy = null; //辅助指针, 帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            }
            curBoy.setNext(boy);
            curBoy = boy;
            curBoy.setNext(first);
        }
    }


    /**
     * 根据用户的输入, 计算出小孩出圈的顺序
     *
     * @param starNo   表示从第几个小孩开始数
     * @param countNum 表示数几下
     */
    public void countBoy(int starNo, int countNum) {
        //先对传入的数据进行校验
        if (starNo < 1 || starNo > nums) {
            System.out.println("输入的参数有误, 请重新输入");
            return;
        }
        //创建一个辅助指针, 帮助小孩出圈, helper指针指向环形队列的最后一个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后的小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //从第几个开始报数: 小孩报数之前, 先让first和helper移动countNum - 1 次
        for (int i = 0; i < starNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //这里是循环操作, 直到圈中只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点
                System.out.printf("%d号小孩出圈", first.getNo());
                return;
            }
            //开始报数
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first节点就是要出圈的节点
            System.out.printf("%d号小孩出圈", first.getNo());
            //这里剔除一个节点, helper一直都是紧跟在first后面的
            first = first.getNext();
            helper.setNext(first);
        }


    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first.getNo() < 1) {
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能移动, 因此我们需要一个辅助指针来进行遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }

    }
}


//构建一个boy类, 表示一个节点
class Boy {
    private int no;
    private Boy next; //指向下一个节点

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

    public Boy(int no) {
        this.no = no;
    }
}
