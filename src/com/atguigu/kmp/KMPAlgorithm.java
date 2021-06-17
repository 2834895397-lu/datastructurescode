package com.atguigu.kmp;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @Author jacklu
 * @Date 10:49:13 2021/06/17
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    //获取到一个字符串(子串)的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个Next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1, 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j), 我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立才退出
            //这是kmp算法的核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                //一直拿前一个匹配值来进行比较
                j = next[j - 1];
            }

            //当dest.charAt(i) == dest.charAt(j)满足时, 部分匹配表的值就+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配表
     * @return 如果是-1表示没有匹配到, 否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理str1.charAt(i) != str2.charAt(j), 去调整j的大小
            //KMP算法的核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                //一直拿子串不匹配的前一个匹配值作为下标再进行比较
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                //子串下标推进
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

}
