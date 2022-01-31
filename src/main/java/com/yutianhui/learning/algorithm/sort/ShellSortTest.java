package com.yutianhui.learning.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 希尔排序测试
 *
 * @author yutianhui
 * @date 2021/12/23 18:22
 */
public class ShellSortTest {

    public static void main(String[] args) {
        // 待排序的数组
        int[] ints = {78, 45, 12, 54, 87, 21};
        //             0   1   2   3   4   5

        /*
         * length = 6
         * --------------------------
         * 1. grp = 3 , step = 3
         * 54 45 12 78 87 21
         * --------------------------
         * 2. grp = 1 , step = 1
         * 45 12 54 78 21 87
         * 12 45 54 21 78 87
         * 12 45 21 54 78 87
         * 12 21 45 54 78 87
         *
         * */

        // 进行希尔排序
        shellSortWithSwap(ints);
        System.out.println(String.format("希尔排序 [交换式]: %s", Arrays.toString(ints)));
        int[] swap = new int[21];
        for (int i = 0; i < swap.length; i++) {
            swap[i] = new Random().nextInt(4500);
        }
        shellSortWithInsert(swap);
        System.out.println(Arrays.toString(swap));
        System.out.println("---------");


        // ---- 测试希尔排序 - 插入式
        int[] ints2 = new int[100000];
        for (int i = 0; i < ints2.length; i++) {
            ints2[i] = new Random().nextInt(10000);
        }
        long start1 = System.currentTimeMillis();
        shellSortWithInsert(ints2);
        long end1 = System.currentTimeMillis();
        System.out.println("shellSortWithInsert(ints2) 花费时间: " + (end1 - start1) + "ms");
        System.out.println(String.format("抽样检查,5.[%s] 500.[%s] 5000.[%s] 50000.[%s]", ints2[5], ints2[500], ints2[5000], ints2[50000]));


        // ---- 测试希尔排序 - 交换式
        int[] ints1 = new int[100000];
        for (int i = 0; i < ints1.length; i++) {
            ints1[i] = new Random().nextInt(Integer.MAX_VALUE / 4);
        }
        long start = System.currentTimeMillis();
        shellSortWithSwap(ints1);
        long end = System.currentTimeMillis();
        System.out.println("shellSortWithSwap(ints1) 花费时间: " + (end - start) + "ms");
        System.out.println(String.format("抽样检查,5.[%s] 500.[%s] 5000.[%s] 50000.[%s]", ints1[5], ints1[500], ints1[5000], ints1[50000]));

    }

    /**
     * 希尔排序 - 交换式
     * 使用增量进行分组,在分组中进行冒泡排序
     *
     * @param arr 待排序的数组
     */
    public static void shellSortWithSwap(int[] arr) {
        // 使用临时变量
        int temp;
        // 进行数组的分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 冒泡排序的反转形式
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 交换数字
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序 - 插入式
     * 进行分组后,对每组应用插入排序
     *
     * @param ints 待排序的数组
     */
    public static void shellSortWithInsert(int[] ints) {
        int value; // 当前位置元素的值
        int index; // 插入元素的前一个位置索引
        // 进行分组,每次除以2
        for (int grp = ints.length / 2; grp > 0; grp /= 2) {
            // 对每个分组使用插入排序
            for (int i = grp; i < ints.length; i++) {
                // 变量赋值
                value = ints[i]; // 当前要比较的值
                index = i - grp; // 当前元素所属组中的上一个元素的索引
                // 进行查找插入
                while (index >= 0 && value < ints[index]) {
                    ints[index + grp] = ints[index];
                    index -= grp;
                }
                // 找到插入位置插入
                if (index != i - grp) ints[index + grp] = value;
            }

        }

    }


}
