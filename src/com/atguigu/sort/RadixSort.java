package com.atguigu.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @Author jacklu
 * @Date 21:42:30 2021/03/27
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {34, 89, 45, 34, 77, 12, 22, 34};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序方法
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {


        /**
         * 定义一个二位数组, 表示10个桶, 每个桶就是一个一维数组
         * 说明:
         *      1. 二位数组包含了10个一维数组
         *      2. 为了防止在放入数的时候, 数据溢出, 则每个一维数组(桶), 大小定位arr.length
         *      3. 明确, 基数排序是使用空间换时间的经典算法
         */
        int[][] bucket = new int[10][arr.length];

        /**
         * 为了记录每个桶中, 实际存放了多少数据, 我们定义一个以为数组来记录各个桶的放入数据的个数
         * 如: bucketElementCounts[0], 记录就是bucket[0]桶的放入数据个数
         */
        int[] bucketElementCounts = new int[10];


        //首先要得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        //使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应位进行处理, 第一次是各位, 第二次是百位....
            for (int j = 0; j < arr.length; j++) {
                //出去每个元素对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标一次取出数据, 放入原数组中)
            int index = 0;
            //遍历每一桶, 并将桶中是数据, 放入到原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据, 我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶, 依次取出, 放入原数组中
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }
                //取完之后, 桶中的数据标记为0
                bucketElementCounts[k] = 0;
            }
        }
    }
}
