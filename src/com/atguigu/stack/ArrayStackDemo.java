package com.atguigu.stack;

import java.util.EmptyStackException;

/**
 * @Author jacklu
 * @Date 13:30:47 2021/03/21
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

        //测试我们的栈
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);
        arrayStack.pop();
        arrayStack.list();
    }
}

//定义一个ArrayStack表示栈
class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1; //表示栈顶, 初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 -push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满, 不能入栈");
            return;
        }
        stack[++top] = value;
    }

    //出栈 -pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空, 没有数据");
        }
        return stack[top--];
    }

    //遍历栈
    public void list() {
        int temp = top; //只是遍历, 不改变栈的结构
        while (temp != -1) {
            System.out.printf("%d\t", stack[temp--]);
        }
    }

}