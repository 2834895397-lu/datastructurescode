package com.atguigu.stack;

/**
 * @Author jacklu
 * @Date 14:48:29 2021/03/21
 */
public class Calculator {
    public static void main(String[] args) {
        //创建一个表达式
        String expression = "3+2*6-12";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //每次将扫描得到char保存到ch中
        String keepNum = ""; //用于拼接多位数
        //开始while循环遍历表达式
        while (true) {
            //依次得到表达式的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么, 如果是操作符
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符, 就进行比较, 如果当前的操作符的优先级小于或者等于栈中的操作符,
                    //就需要从数栈中pop出两个数, 再从符号栈中pop出一个符号, 进行运算, 将得到的结果
                    //压入数栈中, 然后将当前的操作符压入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果压入数栈中
                        numStack.push(res);
                        //将当前操作符压入符号栈中
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符大的优先级大于栈顶的操作符, 就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果符号栈为空, 就直接压入符号栈
                    operStack.push(ch);
                }

            } else {//如果是数字
                /**
                 * 当处理多位数时, 不能发现是一位数就立即入栈, 因为它可能是多位数
                 * 在处理数的时候, 需要向expression的表达式的index后再看一位, 如果是数就进行扫描
                 * 如果是符号就入栈, 因此我们需要定义一个字符串变量, 用于拼接
                 */
                // numStack.push(ch - 48);//把字符变成数字

                //处理多位数
                keepNum += ch;
                //如果是最后一位数字, 则直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //注意是看后一位, 而不是index++, 如果后一位是运算符, 则直接入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符, 则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //最重要的是把keepNum清空
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕, 就顺序的从数栈和符号栈中Pop出相应的数和符号进行运算, 直到符号栈为空
        //此时数栈只有一个元素, 这个便是运算的结果
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //把运算结果压入数栈中
            numStack.push(res);
        }
        System.out.println(expression + "=" + numStack.pop());
    }
}

//定义一个ArrayStack表示栈
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1; //表示栈顶, 初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //返回当前栈顶的值, 但不是真正的Pop
    public int peek() {
        if (top == -1) {
            throw new RuntimeException("栈为空, 没有元素");
        }
        return stack[top];

    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 -push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满, 不能入栈");
            return;
        }
        stack[++top] = value;
    }

    //出栈 -pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空, 没有数据");
        }
        return stack[top--];
    }

    //遍历栈
    public void list() {
        int temp = top; //只是遍历, 不改变栈的结构
        while (temp != -1) {
            System.out.printf("%d\t", stack[temp--]);
        }
    }

    //返回运算符的优先级, 优先级是由程序员来确定的, 优先级使用数字来确定
    //数字越大, 则优先级越高
    public int priority(int oper) {
        //我们java的数据类型int是可以跟char进行比较的
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定目前的表达式中只有加减乘除
        }
    }

    //判断是不是一个运算符
    public boolean isOper(int val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }


    /**
     * 计算方法
     *
     * @param num1 前面出栈的
     * @param num2 后面出栈的
     * @param oper 操作符
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0; //用于存放计算结果
        switch (oper) {
            //出栈的前一个数字对后一个进行操作
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

}
