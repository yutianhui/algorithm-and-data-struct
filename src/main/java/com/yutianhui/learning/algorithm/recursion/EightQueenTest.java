package com.yutianhui.learning.algorithm.recursion;

import java.util.Arrays;

/**
 * 使用递归的方式来解答八皇后问题
 *
 * @author yutianhui
 * @date 2021/12/20 19:25
 */
public class EightQueenTest {

    // 存放结果的数组
    private static int[] array = new int[8];
    // 记录解的个数
    private static int count = 0;
    // 调用方法的次数
    private static int invokeNum = 0;

    public static void main(String[] args) {
        resolve(0); //开始计算结果
        System.out.println(String.format("----------- \n统计信息 -> 解决办法总共有: %s , 方法调用次数: %s", count, invokeNum));
    }

    /**
     * 计算八皇后问题的方法
     *
     * @param n 放入第n个皇后,也代表这第n行
     */
    public static void resolve(int n) {
        // 调用次数加一
        invokeNum++;
        // 终止条件
        if (n == array.length) {
            showArray(array);
            return;
        }
        // 继续递归实验
        // 总共循环8次
        for (int i = 0; i < array.length; i++) {
            // 将皇后从第一列开始不断向前实验
            array[n] = i;
            // 判断当前位置的皇后是否和前面的冲突
            // 1.不和前面的皇后在一列,在一列的条件array[n] == array[m]
            // 2.不和前面的皇后在同一条斜线上,在同一条斜线上的条件是 [数组值(array[n],array[m])的差值等于索引的差值(n,m)]
            // array[n]是当前的皇后,array[m]是前面的皇后
            boolean flag = true;
            for (int m = 0; m < n; m++) {
                // 发现冲突
                if (array[m] == array[n] || (Math.abs(n - m) == Math.abs(array[n] - array[m]))) {
                    flag = !flag;
                    break; //跳出循环
                }
            }
            // 如果不冲突,再向下放皇后
            if (flag) {
                resolve(n + 1);
            }
            // 冲突就后移继续判断
        }

    }

    /**
     * 打印数组的元素
     *
     * @param solve 将要打印的数组
     */
    public static void showArray(int[] solve) {
        /*
         * 皇后使用 ⑤ 代替
         * 网格使用 0 代替
         * */
        count++;
        // 打印图像
        String[][] strings = makeMap();
        for (int i = 0; i < solve.length; i++) {
            strings[i][solve[i]] = "⑧";
        }
        // 组织输出
        System.out.println("------" + count + "------");
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] string : strings) {
            for (String s : string) {
                stringBuilder.append(s).append("\t");
            }
            stringBuilder.append("\n");
        }
        System.out.print(stringBuilder);

    }


    /**
     * 8x8的二维数组 内容为 "0"
     */
    public static String[][] makeMap() {
        // 创建8x8的字符串数组
        String[][] strss = new String[8][8];
        // 填充内容
        for (String[] strings : strss) {
            Arrays.fill(strings, "0");
        }
        return strss;
    }


}
