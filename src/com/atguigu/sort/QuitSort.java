package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Author jacklu
 * @Date 10:26:02 2021/03/27
 */
public class QuitSort {
    public static void main(String[] args) {
        int[] arr = {34, 89, 45, 34, 77, 12, -22, 34};
        quitSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quitSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        int pivot = arr[(left + right) / 2]; //中轴
        int temp = 0; //临时变量, 作为交换时使用
        //while循环的目的就是让比pivot值小放到左边
        //比pivot值大放到右边
        if (left == right) {
            return;
        }
        while (l < r) {
            //在pivot的左边一直找, 找到大于等于pivot的值, 才退出
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot的右边一直找, 找到小于等于pivot的值, 才退出
            while (arr[r] > pivot) {
                r--;
            }
            //说明pivot两边的值左边比pivot小, 右边比pivot大
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            //交换完成之后, 如果发现arr[l] == pivot, 则右指针前移, 理解这一点很关键
            if (arr[l] == pivot) {
                //左边不动, 移右边的指针
                r--;
            }
            //交换完成之后, 如果发现arr[r] == pivot, 则左指针后移, 理解这一点很关键
            if (arr[r] == pivot) {
                //右边不动, 移左边的指针
                l++;
            }
        }
        //如果 l == r, 必须l++, r--, 否则会出现栈溢出
        if (r == l) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quitSort(arr, left, r);
        }
        //向右递归
        if (left < r) {
            quitSort(arr, l, right);
        }

    }
}
