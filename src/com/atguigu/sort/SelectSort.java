package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Author jacklu
 * @Date 15:44:57 2021/03/26
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 999, 45, 2, 66, 43};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序:
     * 选择出最小的放在前面
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int minValue = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (minValue > arr[j]) {
                    minIndex = j;
                    minValue = arr[j];
                }
            }
            if (minIndex != i) { //交换
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
        }

    }
}
