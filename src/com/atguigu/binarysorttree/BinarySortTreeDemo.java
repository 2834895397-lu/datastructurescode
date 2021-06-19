package com.atguigu.binarysorttree;

import javax.xml.ws.EndpointReference;

/**
 * @Author jacklu
 * @Date 14:50:42 2021/06/18
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }

        //中序遍历二叉排序树(二叉排序树的中序遍历是升序)
        binarySortTree.infixOrder();
    }
}

class Node {
    int value;
    Node left;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加节点, 注意需要满足二叉排序树的要求
     *
     * @param node 要添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value <= this.value) {
            if (this.left == null) {
                this.left = node;//添加节点
                return;
            } else {
                this.left.add(node);
            }
        }
        if (node.value > this.value) {
            if (this.right == null) {
                this.right = node;//添加节点
                return;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}

//创建二叉树
class BinarySortTree {
    private Node root;

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空, 不能遍历!");
        }
    }
}
