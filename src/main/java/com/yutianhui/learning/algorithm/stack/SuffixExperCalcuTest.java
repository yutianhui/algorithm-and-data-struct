package com.yutianhui.learning.algorithm.stack;

import java.util.Stack;

/**
 * TODO
 *
 * @author yutianhui
 * @date 2021/12/11 15:46
 */
public class SuffixExperCalcuTest {

    public static void main(String[] args) {
        /**
         * 使用逆波兰表达式进行计算
         * 1.支持多位数字
         * 2.支持小括号
         * 将中缀表达式转换为后缀表达式进行操作
         * */

        // 记录开始时间
        long start = System.currentTimeMillis();

        //中缀表达式,99
        String expression = "155+356*15*(45+45/9)+120/(2*5)";
        // 将中缀表达式转换为后缀表达式
        String suffixExpression = generateSuffixExpre(expression); //后缀表达式
        // 后缀表达式计算
        Integer integer = suffixCalcu(suffixExpression.split(" "));
        // 输出结果
        System.out.printf("中缀表达式: %s \n后缀表达式: [%s]\n结果: %s\n", expression, suffixExpression, integer);

        // 计时结束
        long end = System.currentTimeMillis();
        System.out.println("运行时间: " + (end - start) + "ms");

    }


    /**
     * 中缀表达式转换为后缀表达式算法实现
     *
     * @param expression 中缀表达式
     * @return 后缀表达式字符串 "5 7 + 12 * 45 - 52 2 / +"
     */
    public static String generateSuffixExpre(String expression) {
        // 将字符串转为字符数组
        char[] chars = expression.toCharArray();
        // 初始化两个栈
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        // 索引指针
        Integer pointer = 0;
        // 判断标志进行操作
        boolean flag = false;
        // 进行循环扫描
        while (true) {
            // 当前的数或者符号
            String num = "";
            // 循环查找多位数
            while (true) {
                // 扫描到最后了
                if (pointer == chars.length) {
                    flag = !flag;
                    break;
                }
                if ((chars[pointer++] + "").matches("\\d")) {
                    // 是数字
                    num += chars[pointer - 1];
                } else {
                    // 不是数字,跳出循环
                    break;
                }
            }
            // 是操作数的话,添加到stack2中
            if (!num.isEmpty()) stack2.push(num);
            // 扫描到尾部
            if (flag) break;
            // 扫描到符号
            String oper = chars[pointer - 1] + "";
            // 是左括号,直接进stack1
            if ("(".equals(oper)) {
                stack1.push(oper);
            }
            // 是右括号
            if (")".equals(oper)) {
                // stack1中弹出运算符到stack2中,直到弹出一个左括号停止
                // 左括号不用加入到stack2中
                while (true) {
                    String pop = stack1.pop();
                    if ("(".equals(pop)) break;
                    stack2.push(pop);
                }
            }
            // 是运算符
            if (CalcuArrayStack.level(oper) != 0) {
                while (true) {
                    // 如果stack1为空或者栈顶位置是一个左括号,直接添加到stack1
                    if (stack1.isEmpty() || stack1.peek().equals("(") || CalcuArrayStack.level(oper) > CalcuArrayStack.level(stack1.peek())) {
                        // stack1为空的,直接加入运算符
                        stack1.push(oper);
                        break;
                    } else {
                        // 将stack1栈顶的运算符弹出加入到stack2,重新比较
                        stack2.push(stack1.pop());
                    }
                }
            }
        }

        // 反转stack2中的顺序,得到结果
        while (true) {
            stack1.push(stack2.pop());
            if (stack2.isEmpty()) break;
        }
        // 结果
        String result = "";
        // 循环添加
        while (true) {
            String pop = stack1.pop();
            result += pop;
            if (stack1.isEmpty()) break;
            result += " ";
        }
        return result;
    }


    /**
     * 后缀表达式计算,返回结果
     * 扫描,碰到数字填充到栈中,碰到符号,从栈中弹出两个数字进行计算,将结果存入栈中
     *
     * @param suffixs 分解的后缀表达式
     * @return 计算结果
     */
    public static Integer suffixCalcu(String[] suffixs) {
        // 数组长度小于3
        if (suffixs.length < 3) {
            System.out.println("传递的数组不合法......");
            return -Integer.MAX_VALUE;
        }
        // 创建栈
        Stack<String> numStack = new Stack<>();
        // 循环计算
        for (int i = 0; i < suffixs.length; i++) {
            // 当前字符串
            String curr = suffixs[i];
            if (curr.matches("\\d+")) {
                // 是数字直接添加到栈中
                numStack.push(curr);
            } else {
                // 是符号,将从数栈中提取两个数字进行运算
                // 将结果存到数栈中
                String num1 = numStack.pop();
                String num2 = numStack.pop();
                Integer calcu = CalcuArrayStack.calcu(num2, num1, curr);
                numStack.push(calcu.toString());
            }
        }
        return Integer.valueOf(numStack.pop());
    }

}
