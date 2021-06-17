package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Author jacklu
 * @Date 14:55:40 2021/03/26
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 2, 55, 2};
        bubbleSort(arr);
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {//总共要n-1趟循环
        int temp = 0;//临时变量

        /**
         * 优化:
         *      注意一个点, 就是当一次循环下来, 没有进行过一次交换, 则说明了已经是有序的了
         *      所以我们需要一个标志位来标志一趟循环下来是否有进行过交换
         */

        boolean flag = false;

        for (int i = 0; i < arr.length - 1; i++) { //事件复杂度为(n^2)
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //优化的代码
            if (!flag) {
                break;
            } else {
                flag = false; //重置flag, 进行下次判断!!!
            }
            System.out.println("第" + (i + 1) + "趟循环");
            System.out.println(Arrays.toString(arr));
        }
    }
}
