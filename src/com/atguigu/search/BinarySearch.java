package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jacklu
 * @Date 12:27:10 2021/03/28
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {-1, 9, 11, 11, 11, 89};
        //使用二分查找法前提是数组已经是排好序的了
        int index = binarySearch(arr, 0, arr.length - 1, 11);
        System.out.println(index);
        ArrayList resIndex = binarySearchAll(arr, 0, arr.length - 1, 11);
        System.out.println(resIndex);
    }

    /**
     * 返回所查找的数在的下标
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal 要查找的值
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //退出的条件
        if (left > right || findVal > arr[arr.length - 1] || findVal < arr[0]) {
            return -1;
        }

        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 返回所查找的数在的所有下标
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return
     */
    public static ArrayList binarySearchAll(int[] arr, int left, int right, int findVal) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //退出的条件
        if (left > right || findVal > arr[arr.length - 1] || findVal < arr[0]) {
            return null;
        }

        if (findVal > midVal) {
            return binarySearchAll(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearchAll(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndex = new ArrayList<>();
            resIndex.add(mid);
            int indexToLeft = mid - 1;
            //向左扫描, 继续寻找相同的数
            while (indexToLeft >= 0) {
                if (findVal == arr[indexToLeft]) {
                    resIndex.add(indexToLeft);
                    indexToLeft--;
                } else {
                    break;
                }
            }
            //向右扫描, 继续寻找相同的数
            int indexToRight = mid + 1;
            //向左扫描, 继续寻找相同的数
            while (indexToRight <= arr.length - 2) {
                if (findVal == arr[indexToRight]) {
                    resIndex.add(indexToRight);
                    indexToRight++;
                } else {
                    break;
                }
            }
            return resIndex;
        }
    }


}
