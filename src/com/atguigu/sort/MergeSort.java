package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Author jacklu
 * @Date 12:16:53 2021/03/27
 */
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {3, 4, 5, 66, 43, 2, -3, 6, 1, 77};
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分＋合
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法(治)
     *
     * @param arr   排序的原数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //初始化i, 左边有序序列的初始索引
        int j = mid + 1; //初始化j, 右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        /**
         * 一:
         *  先把左右两边(有序)的数据按照规则填充到temp数组中
         *  直到左右两边的有序序列, 有一边处理完毕位置
         */
        while (i <= mid && j <= right) {//继续
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }

        }

        /**
         * 二:
         *  把剩余数据的一边的数据依次全部填充到temp中
         */
        while (i <= mid) {//左边的有序序列还有剩余的元素, 就全部填充到temp中
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {//有边的有序序列还有剩余的元素, 就全部填充到temp中
            temp[t] = arr[j];
            t++;
            j++;
        }

        /**
         * 三:
         *  将temp数组的元素拷贝到arr
         *  注意, 并不是每次都拷贝所有
         */
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
