package com.atguigu.queue;

import java.util.Scanner;

/**
 * @Author jacklu
 * @Date 20:31:38 2021/03/19
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        //创建一个环形队列
        CircleArray arrayQueue = new CircleArray(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 推出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是: %d\n", res);
                    } catch (Exception e) {

                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是:%d\n", res);
                    } catch (Exception e) {

                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }


    }
}

class CircleArray {
    private int maxSize;
    private int front; //初始值为0, 指向队列的第一个元素, 出队后加1取模
    private int rear; //初始值为0, 指向队列的最后一个元素的后一个位置, 入队后加1取模
    private int[] arr; //空出一个作为约定

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否为满
        if (isFull()) {
            System.out.println("队列满, 不能假如数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据, 出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空, 不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1. 先把front对应的值保存到一个临时变量中
        //2. 将front后移, 考虑取模
        //3. 将临时保存的标量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空, 没有数据....");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列的有效数据的个数
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空, 没有数据.....");
        }
        return arr[front];
    }

}
