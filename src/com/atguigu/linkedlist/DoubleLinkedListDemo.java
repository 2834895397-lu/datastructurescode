package com.atguigu.linkedlist;

/**
 * @Author jacklu
 * @Date 22:59:59 2021/03/20
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        //双向列表的测试
        System.out.println("双向列表的测试>>>>>>>>>>");
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建一个链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero4);

        doubleLinkedList.del(new HeroNode2(2, "", ""));
        doubleLinkedList.update(new HeroNode2(3, "jacklu", "lulu"));
        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空....");
        }
        //因为头节点不能动, 因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    //删除节点(根据编号删除)
    public void del(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean enableDel = false; //标志是否找到待删除的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                enableDel = true;
                break;
            }
            temp = temp.next;
        }
        if (enableDel) {
            //删除节点
            temp.pre.next = temp.next;
            //可能删除最后一个节点
            if (temp.next.pre != null) {
                temp.next.pre = temp.pre;
            }
        }

    }

    //按顺序添加到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动, 因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //根据编号来修改节点信息
    public void update(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean enableUpdate = false;
        while (true) {
            if (temp.next == null) {
                break;
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

}

//定义HeroNode2, 每个HeroNode对象是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; //指向下一个节点   默认为null
    public HeroNode2 pre;   //指向前一个节点 默认为null

    public HeroNode2(int no, String name, String nickName) {
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
