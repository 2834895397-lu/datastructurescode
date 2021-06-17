package com.atguigu.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author jacklu
 * @Date 16:26:01 2021/03/26
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {3, 2, 4, 2, 55, 2};
        //搞八千个数组进行测试:
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        String date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(date1);
        insertSortFor(arr);
        String date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //int[] arr = {3, 2, 4, 2, 55, 2, -1};
        System.out.println(date2);
        //insertSortWhile(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSortFor(int[] arr) {
        //插入排序的思想就是假定前面已经是有序的了, 把后面的插入到前面的合适的位置
        for (int i = 1; i < arr.length; i++) {//i是要插入的元素下标
            int index = -1;
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {//j及往前是有序的, 假定是从小到大
                //往后挪, 这里的比较切记不要用arr[i], 因为arr[i]有可能被arr[j]覆盖, 并不是初始值!!!
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j];
                    index = j;
                } else {
                    break;
                }
            }
            //把值插进去
            if (index != -1) {
                arr[index] = temp;
            }
            //System.out.println("第"+(i)+"轮插入结果:" + Arrays.toString(arr));
        }
    }

    public static void insertSortWhile(int[] arr) {
        //插入排序的思想就是假定前面已经是有序的了, 把后面的插入到前面的合适的位置
        for (int i = 1; i < arr.length; i++) {//i是要插入的元素下标
            int index = i - 1;
            int temp = arr[i];
            while (index >= 0 && temp < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            //要插入的位置是index + 1
            arr[index + 1] = temp;
            System.out.println("第" + (i) + "轮插入结果:" + Arrays.toString(arr));
        }
    }
}
