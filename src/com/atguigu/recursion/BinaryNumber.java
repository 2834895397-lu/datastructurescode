package com.atguigu.recursion;

import java.math.BigInteger;

/**
 * @Author jacklu
 * @Date 22:44:54 2021/03/21
 */
public class BinaryNumber {
    public static void main(String[] args) {
        BigInteger number = new BigInteger("12");
        String binaryNum = getBinaryNum(number);
        System.out.println(number + " 的二进制数为: " + binaryNum);
    }

    public static String getBinaryNum(BigInteger i) {
        BigInteger k = i.divide(new BigInteger("2"));
        BigInteger m = i.mod(new BigInteger("2"));
        if (!k.equals(new BigInteger("0"))) {
            i = k;
            return getBinaryNum(i) + m;
        }
        return m + "";
    }
}
