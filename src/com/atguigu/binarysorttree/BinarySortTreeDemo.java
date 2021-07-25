package com.atguigu.binarysorttree;

import javax.xml.ws.EndpointReference;

/**
 * @Author jacklu
 * @Date 14:50:42 2021/06/18
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }

        //中序遍历二叉排序树(二叉排序树的中序遍历是升序)
        binarySortTree.infixOrder();

        //测试删除叶子节点
        binarySortTree.delNode(2);
        System.out.println("===========删除之后===========");
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

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (value == this.value) {//找到就是该节点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前节点, 向左子树递归查找
            //如果左子树节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前节点, 向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    public Node searchParent(int value) {
        //如果当前节点的左子节点或者右子节点就是要删除的节点, 返回当前节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //注意排序二叉树是跟当前节点比较, 而不是跟当前节点的左子节点比较
            if (this.left != null && value < this.value) {
                return this.left.searchParent(value);//向左子树递归
            } else if (this.right != null && value >= this.value) {
                return this.right.searchParent(value);//向右子树递归
            } else {
                return null;
            }
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

    /**
     * 查找节点
     *
     * @param value 节点的值
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找指定节点的父节点
     *
     * @param value 要查找的值
     * @return 返回指定值的父节点
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //找到要删除的节点 targetNode
            Node targetNode = root.search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }//如果有要删除的节点, 并且root不等于null, 我们就要判断是不是只有一个root节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //找到targetNode的父节点
            Node parent = root.searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是左节点还是右节点
                if (parent.left != null && parent.left.value == value) {//是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//是右子节点
                    parent.right = null;
                }
                /**
                 * 注意如果是叶子节点不能世界targetNode=null来进行置空, 因为targetNode是栈里面的变量
                 * 直接targetNode=null直接就是改变了栈里面的变量指向, 并没有改变原来的树结构
                 */
            }
        }

    }
}
