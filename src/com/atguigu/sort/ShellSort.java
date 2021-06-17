package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author jacklu
 * @Date 20:39:15 2021/03/26
 */
public class ShellSort {
    public static void main(String[] args) {

        //搞八千个数组进行测试:
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        String date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(date1);
        //shellSort1(arr);
        shellSort2(arr);
        String date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //int[] arr = {3, 2, 4, 2, 55, 2, -1};
        System.out.println(date2);

        System.out.println(Arrays.toString(arr));
        System.out.println(arr.length);

    }

    /**
     * 希尔排序交换法, 效率没有移位法高
     *
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        int temp = 0;
        int count = 0;
        //步长逐渐缩小
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的所有的元素(共gap组, 没组有个元素), 步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长的元素, 则加上步长后的元素要往前冒, 就是把最小的往前送
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第" + (++count) + "轮的排序结果:" + Arrays.toString(arr));
        }

    }

    /**
     * 希尔排序移位法
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        //增量gap, 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素, 逐个对其所在的数组进行直接插入
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j]; //要插入的元素
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环之后, 就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
