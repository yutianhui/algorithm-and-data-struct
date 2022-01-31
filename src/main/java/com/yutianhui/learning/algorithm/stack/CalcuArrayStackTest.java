package com.yutianhui.learning.algorithm.stack;

/**
 * TODO
 *
 * @author yutianhui
 * @date 2021/12/10 20:13
 */
public class CalcuArrayStackTest {

    public static void main(String[] args) {

        /*
         * 使用栈实现计算器效果
         * 1. 可以实现多位数计算
         * */

        // 定义的表达式
        String exper = "1+1*45/9+102*45+7895";
        // 创建两个栈
        CalcuArrayStack numStack = new CalcuArrayStack(20); //数栈
        CalcuArrayStack operStack = new CalcuArrayStack(20); //操作符栈
        char[] chars = exper.toCharArray();
        int pointer = 0;
        boolean isFlag = false;
        // 循环扫描
        while (true) {
            // 数字
            String num = "";
            while (true) {
                if (pointer == chars.length) {
                    isFlag = !isFlag;
                    break;
                }
                String charStr = chars[pointer++] + "";
                if (CalcuArrayStack.level(charStr) == 0) {
                    // 是数字
                    num += charStr;
                } else {
                    // 碰到运算符号
                    break;
                }
            }
            // 添加到数栈中
            if (!num.equals("")) numStack.push(num);
            if (isFlag) break; // 中断循环
            // 操作符号
            String oper = chars[pointer - 1] + "";
            // 添加到操作栈
            if (operStack.isEmpty()) {
                // 直接添加
                operStack.push(oper);
            } else {
                // 判断优先级
                String topString = operStack.getTopString();
                // 当前操作符优先级小于等于前面的操作符
                if (CalcuArrayStack.level(oper) <= CalcuArrayStack.level(topString)) {
                    // 数栈弹出两个数字
                    String num1 = numStack.pop();
                    String num2 = numStack.pop();
                    // 操作符栈弹出一个
                    String pop = operStack.pop();
                    // 计算结果入数栈
                    Integer calcu = CalcuArrayStack.calcu(num2, num1, pop);
                    numStack.push(calcu.toString());
                    // 当前操作符入操作符栈
                    operStack.push(oper);
                } else {
                    // 当前操作符号优先级大于前面的
                    // 数栈弹出一个数字,再向前扫描一个数字,和当前运算符运算
                    String parseInt = numStack.pop();
                    String next = "";
                    while (true) {
                        if (pointer == chars.length) break;
                        String charStr = chars[pointer++] + "";
                        if (CalcuArrayStack.level(charStr) == 0) {
                            // 是数字
                            next += charStr;
                        } else {
                            // 碰到运算符号
                            pointer--;
                            break;
                        }
                    }
                    Integer calcu = CalcuArrayStack.calcu(parseInt, next, oper);
                    // 结果存入数栈
                    numStack.push(calcu.toString());
                }

            }

        }
        // 计算结果
        String num1 = numStack.pop();
        String num2 = numStack.pop();
        int calcu = CalcuArrayStack.calcu(num2, num1, operStack.pop());
        System.out.printf("[计算完成] --> %s = %s", exper, calcu);
    }

}

class CalcuArrayStack {

    private String[] datas; //栈的数据
    private int head = -1; //栈顶
    private int count = 0; //栈数据量
    private int maxSize = 0; //最大容量

    public CalcuArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.datas = new String[maxSize];
    }

    // 添加元素
    public void push(String string) {
        if (isFull()) {
            System.out.println("栈达到最大数量...");
            return;
        }
        datas[++head] = string;
        count++;
    }

    // 弹出新的元素
    public String pop() {
        if (isEmpty()) {
            System.out.println("栈是空的了...");
            return null;
        }
        String result = datas[head];
        datas[head] = null;
        head--;
        count--;
        return result;
    }

    // 展示show()
    public void show() {
        if (isEmpty()) {
            System.out.println("栈是空的....");
            return;
        }
        System.out.println("<< 栈内数据如下");
        for (int i = count; i > 0; i--) {
            System.out.printf("[%d] == <%s> \n", i - 1, datas[i - 1]);
        }
        System.out.println("栈元素数量: " + count + ", 栈顶索引为: " + head);
        System.out.println("展示数据完成 >>");
    }

    // 判断是否是空的
    public boolean isEmpty() {
        return head == -1;
    }

    // 判断是否已经满了
    public Boolean isFull() {
        return head == maxSize - 1;
    }

    // 获取栈顶指针
    public int getHead() {
        return head;
    }

    // 获取最大的数量
    public int getMaxSize() {
        return maxSize;
    }

    // 获取栈中的数据
    public int getCount() {
        return count;
    }

    //获取顶部的元素
    public String getTopString() {
        return datas[head];
    }

    /**
     * 获取运算符的优先级 <br>
     * + 和 - --> 1 <br>
     * * 和 / --> 2 <br>
     * 啥也不是 --> 0
     *
     * @param operation 运算符
     */
    public static int level(String operation) {
        if (operation.equals("-") || operation.equals("+")) {
            return 1;
        }
        if (operation.equals("*") || operation.equals("/")) {
            return 2;
        }
        return 0;
    }

    /**
     * 进行计算操作,num1 oper num2进行计算
     *
     * @param num1 数字
     * @param num2 数字
     * @param oper 操作符号
     */
    public static int calcu(String num1, String num2, String oper) {
        int int1 = Integer.parseInt(num1);
        int int2 = Integer.parseInt(num2);
        switch (oper) {
            case "+":
                return int1 + int2;
            case "-":
                return int1 - int2;
            case "*":
                return int1 * int2;
            case "/":
                return int1 / int2;
        }
        throw new RuntimeException("错误的运算符....");
    }

}
