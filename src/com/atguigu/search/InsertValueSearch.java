package com.atguigu.search;

/**
 * @Author jacklu
 * @Date 14:07:22 2021/03/28
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = {-1, 9, 11, 11, 11, 89};
        //使用二分查找法前提是数组已经是排好序的了
        int index = insertValueSearch(arr, 0, arr.length - 1, 9);
        System.out.println(index);
    }

    /**
     * 这个查找的原理跟二分查找法一样, 只不过是中间的比较值mid不一样
     * 插值查找法的mid的公式为:
     * left + (right - left) * (findVal -arr[left]) / (arr[right] - arr[left])
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 查找值
     * @return 如果找到, 就返回对应的下标, 如果没有找到, 就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找函数执行.......");
        //结束条件
        if (left > right || findVal > arr[arr.length - 1] || findVal < arr[0]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
