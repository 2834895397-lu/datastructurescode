package com.atguigu.linkedlist;

import java.text.BreakIterator;
import java.util.Stack;

/**
 * @Author jacklu
 * @Date 11:16:56 2021/03/20
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //进行测试, 先创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //singleLinkedList.add(hero1);
        //singleLinkedList.add(hero2);
        //singleLinkedList.add(hero3);
        //singleLinkedList.add(hero4);
        //singleLinkedList.list();

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        /*singleLinkedList.update(new HeroNode(5, "jacklu", "lulu"));
        singleLinkedList.del(new HeroNode(1, "jacklu", "lulu"));
        singleLinkedList.list();
        int length = SingleLinkedList.getLength(singleLinkedList.getHead());
        System.out.println("链表长度为: " + length);
        int n = 1;
        HeroNode lastIndexNode = singleLinkedList.findLastIndexNode(n);
        System.out.println("倒数第" + n + "个为: " + lastIndexNode);
        System.out.println("链表反转*****************");
        singleLinkedList.reverseList();
        singleLinkedList.list();
        System.out.println();
        singleLinkedList.reversePrint();
        */
        singleLinkedList.update(new HeroNode(2, "jacklu", "lulu"));
        singleLinkedList.list();
    }
}

//定义SingleLinkedList, 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //使用栈将链表逆序打印
    public void reversePrint() {
        if (head.next == null) {
            return;
        }
        //创建一个栈, 将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next; //cur后移, 这样就可以压入下一个节点
        }
        while (stack.size() > 0) {
            System.out.printf("%s\t", stack.pop());
        }
    }


    //链表的反转: 新建一个链表, 将原来的链表第一个节点取下来, 接到新链表的head上, 第二个也接到head上, 它的next指向head.next
    public void reverseList() {
        //如果当前链表为空, 或者只有一个节点, 无需反转, 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next; //!!! cur的指向指向reverseHead的下一个
            reverseHead.next = cur;
            cur = next;
        }
        //将head.next指向reverseHead.next, 实现单链表的反转
        head.next = reverseHead.next;
    }

    //按顺序添加
    public void add(HeroNode heroNode) {
        //因为head节点不能动, 因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后
            temp = temp.next;
        }
        //当退出while循环时, temp就指向了链表的最后
        //将这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //查找单链表中的倒数第k个节点

    /**
     * 思路: index表示倒数第几个
     * 1. 首先要遍历链表得到总长度size
     * 2. 得到size之后, 我们从链表的第一个开始遍历(size-index)个, 就可以得到
     */
    public HeroNode findLastIndexNode(int index) {
        if (head.next == null) {
            return null;//没有找到
        }
        int size = getLength(head);
        //先做个校验
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;

    }

    //按照编号来添加
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动, 因此我们仍然通过一个辅助指针(变量)来帮助我们找到添加的位置
        //因为单链表, 因此我们找到的temp是位于添加位置的前一个节点否则插入不了
        HeroNode temp = head;
        boolean flag = false; //flag标志添加的编号是否存在, 默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后了
                break;
            }
            if (temp.next.no > heroNode.no) {//找到位置, 就在temp后面插入
                break;
            }
            if (temp.next.no == heroNode.no) {//说明编号已经存在
                System.out.println("编号已经存在, 不能添加 " + heroNode.no);
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //添加节点
        if (!flag) {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //获取链表的长度(不包括头节点)
    public static int getLength(HeroNode head) {
        HeroNode temp = head;
        int count = 0;
        if (temp.next == null) {
            return 0;
        }
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    //根据编号来修改节点信息
    public void update(HeroNode heroNode) {
        HeroNode temp = head;
        boolean enableUpdate = false;
        while (true) {
            if (temp.next == null) {
                return;
            }
            if (temp.no == heroNode.no) {
                enableUpdate = true;
                break;
            }
            temp = temp.next;
        }
        if (enableUpdate) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
            System.out.println("修改成功!");
            return;
        }
        System.out.println("修改失败, 没有这个编号!" + heroNode.no);
    }

    //删除节点(根据编号删除)
    public void del(HeroNode heroNode) {
        HeroNode temp = head;
        boolean enableDel = false; //标志是否找到待删除的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                enableDel = true;
                break;
            }
            temp = temp.next;
        }
        if (enableDel) {
            temp.next = temp.next.next;
        }

    }

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空....");
        }
        //因为头节点不能动, 因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表的最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    public HeroNode getHead() {
        return head;
    }
}


//定义HeroNode, 每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
