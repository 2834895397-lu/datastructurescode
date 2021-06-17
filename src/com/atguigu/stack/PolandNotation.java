package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author jacklu
 * @Date 13:08:20 2021/03/22
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        //(3+4)×5 => "3 4 + 5 × 6 -"
        //说明为了方便, 逆波兰表达式的数字和符号使用空格隔开
       /* String suffixExpression = "3 4 + 5 * 6 -";
        List<String> rpnList = getListString(suffixExpression);
        int calculate = calculate(rpnList);*/
        //将中缀表达式转成对应的List
        String expression = "(3+4)*5-61";
        List<String> strings = toInfixExpressionList(expression);
        //将中缀表达式list转成后缀表达式list
        List<String> suffixExpressionList = parseSuffixExpressionList(strings);
        int res = calculate(suffixExpressionList);
        System.out.println(expression + " = " + res);

        //计算后缀表达式的结果


        //System.out.println(strings);

    }

    //将一个逆波兰表达式装入list中
    public static List<String> getListString(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String string : strings) {
            list.add(string);
        }
        return list;
    }

    /**
     * 逆波兰表达式的运算步骤:
     * 1. 从左至右扫描, 将3和4压入栈
     * 2. 遇到+运算符, 因此弹出3和4(4为栈顶元素, 3为次顶元素)计算出3+4的值, 然后将值压入栈中
     * 3. 将5入栈
     * 4. 接下来就是×运算, 因此弹出5和7相乘, 然后结果入栈
     * 5. 最后是-运算符, 数栈弹出两个数, 次栈顶减去栈顶得出结果
     *
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls) {
        //创建一个栈, 只需要一个栈即可
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            //这里使用正则表达式取出整数
            if (item.matches("\\d+")) {//如果是整数
                stack.push(item);
            } else {
                //pop出两个数, 并进行运算, 后面的数对前面的数进行操作, 然后结果再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        //遍历完成之后就可以得出结果了
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将中缀表达式转换成为对应的list
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这是是一个指针, 用于遍历中缀表达式字符串
        String str; //对多位数进行拼接
        char c;//每遍历到一个字符, 据放入到c中
        if (s == null || "".equals(s)) {
            return null;
        }
        do {
            char item = s.charAt(i);
            //如果是符号
            if (item < 48 || item > 57) {
                ls.add(item + "");
                i++;
            } else {//是数字, 看它下一位是否是数字, 是数字要进行拼接
                str = "";
                while (i < s.length() && (s.charAt(i) >= 48) && (s.charAt(i) <= 57)) {
                    str += s.charAt(i);
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 将中缀表达式对应的list转换成后缀表达式
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>(); //符号栈
        //说明: 因为s2这个栈, 在整个转换的过程中, 没有pop操作, 而且后面我们需要逆序输出, 这样比较麻烦
        //因此我们这里就不用栈, 直接使用List<String> s2
        List<String> s2 = new ArrayList<>();//存储中间的结果

        //遍历ls
        for (String item : ls) {
            //如果是一个数, 加入s2中
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //将(弹出s1栈
            } else {//是一个运算符  (大优先级运算符抢入栈)
                //跟s1栈中的运算符进行比较, 如果优先级比s1栈顶的大, 则s1出来压入s2, 如此循环, 然后小的压入s1
                //否则直接压入s1

                //跳出循环的时候有两个可能, 一个是s1栈为空, 一个s1栈顶为左括号, 此时: Operation.getValue("(") = 0最小
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    //s1出栈并添加到s2中
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }

        }

        //循环完毕之后, 将s1栈中的元素一次压入s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

}

///编写一个类, 返回对应的优先级的顺序
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 1;
    private static final int DIV = 1;

    //写一个方法, 返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}

