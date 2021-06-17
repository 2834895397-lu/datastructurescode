package com.atguigu.tree;

import javafx.scene.transform.Rotate;

import java.util.Scanner;

/**
 * @Author jacklu
 * @Date 21:07:15 2021/05/26
 */
public class BinaryTree {
    private HeroNode root;

    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "松江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //先手动创建二叉树, 下次直接用递归创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");//1,2,3,5,4
        binaryTree.preOrder();
        //测试
        System.out.println("中序遍历");//
        binaryTree.infixOrder();
        //测试
        System.out.println("后序遍历");//2,4,3,1
        binaryTree.postOrder();

        //前序遍历查找
        binaryTree.preOrderSearch(4);
        //中序遍历查找
        binaryTree.infixOrderSearch(4);
        //后序遍历查找
        binaryTree.postOrderSearch(4);
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空, 无法遍历!");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空, 无法遍历!");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空, 无法遍历!");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.preOrderSearch(no);
            System.out.println(heroNode);
            return heroNode;
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.infixOrderSearch(no);
            System.out.println(heroNode);
            return heroNode;
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.postOrderSearch(no);
            System.out.println(heroNode);
            return heroNode;
        } else {
            return null;
        }
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历!");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //1. 则判断当前节点的子节点是否为空, 如果不为空, 则递归前序查找
        //2. 如果左递归前序查找, 找到节点, 则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明我们左子树找到
            return resNode;
        }

        //向右递归
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        System.out.println("进入中序遍历!");
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            System.out.println("左递归的返回");
            return resNode;
        }
        if (this.no == no) {
            System.out.println("左递归的返回");
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        System.out.println("右递归的返回");
        return resNode;
    }

    //后序遍历
    public HeroNode postOrderSearch(int no) {
        System.out.println("进入后续遍历!");
        //判断当前节点的左子节点是否为空, 如果不为空递归后续查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//说明在左子树找到
            return resNode;
        }
        //如果左子树没有找到, 则向右子树进行递归遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {//说明右子树找到了
            return resNode;
        }
        //如果左右子树都没有找到, 就比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

}

