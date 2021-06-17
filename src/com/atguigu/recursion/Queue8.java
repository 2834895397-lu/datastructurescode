package com.atguigu.recursion;

/**
 * @Author jacklu
 * @Date 13:01:36 2021/03/26
 */
public class Queue8 {
    //定义一个max表示有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后存放位置的结果, 比如arr = {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];

    public static void main(String[] args) {
        //测试进行玩一把
        Queue8 queue8 = new Queue8();
        queue8.put(0);
    }


    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    /**
     * 查看我们当前存放的第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示放第n+1个皇后
     * @return 返回true表示不冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //位于同一列或者同一对角线
            //判断是否在同一行没有必要, 因为n每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //编写一个方法, 放置第n+1个皇后
    //特别注意: put是每一次递归时, 进入put中都有for
    private void put(int n) {
        if (n == 8) {//当n等于8时, 8个皇后已经放好了
            print();
            return;
        }
        //依次放入皇后并判断是否冲突, i为列数
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n, 放入到改行的第1列
            array[n] = i;
            //判断当前放置第n个皇后到i列时, 是否冲突
            if (judge(n)) {//true为不冲突
                //接着放n+1个皇后, 即开始递归
                put(n + 1);
            }
            //如果冲突, 就继续执行下一轮的for循环array[n] = i, 即将第n个皇后, 放置在本行的后移的一个位置
        }
    }
}
