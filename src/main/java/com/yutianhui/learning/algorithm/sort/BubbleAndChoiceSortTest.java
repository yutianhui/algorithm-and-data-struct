package com.yutianhui.learning.algorithm.sort;

import java.util.Random;

/**
 * 冒泡排序和选择排序测试
 *
 * @author yutianhui
 * @date 2021/12/21 15:18
 */
public class BubbleAndChoiceSortTest {

    public static void main(String[] args) {
        // 创建两个数组进行排序
        int[] ints1 = new int[80000];
        int[] ints2 = new int[80000];
        for (int i = 0; i < ints1.length; i++) {
            ints1[i] = new Random().nextInt(50000);
            ints2[i] = new Random().nextInt(50000);
        }
        // 测试冒泡排序
        long start1 = System.currentTimeMillis();
        bubbleSort(ints1);
        long end1 = System.currentTimeMillis();
        System.out.println("bubbleSort(ints1) 花费时间: " + (end1 - start1) + "ms");
        System.out.println(String.format("抽样检查,[%s] [%s] [%s] [%s]", ints1[5], ints1[500], ints1[5000], ints1[50000]));

        // 测试选择排序
        long start2 = System.currentTimeMillis();
        choiceSort(ints2);
        long end2 = System.currentTimeMillis();
        System.out.println("choiceSort(ints2) 花费时间: " + (end2 - start2) + "ms");
        System.out.println(String.format("抽样检查,[%s] [%s] [%s] [%s]", ints2[5], ints2[500], ints2[5000], ints2[50000]));

        // 测试冒泡排序的逆转形式
//        int[] ints = bubbleSortReverse(new int[]{45, 78, 12, 65, 23, 89});
//        System.out.println(Arrays.toString(ints));


    }

    public static int[] makeInts(int length) {
        int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(length * 5);
        }
        return ints;
    }


    /**
     * 选择排序算法实现
     * 每次挑选出最小/最大的数放到数组的头/尾部
     * 时间复杂度: O(n^2)
     *
     * @param ints 待排序的数组
     */
    public static void choiceSort(int[] ints) {
        int max; // 代表每轮最大的数字,会将每轮最大的数字放到数组的最后端
        int index; // 代表每轮最大数字的索引位置
        for (int i = 0; i < ints.length - 1; i++) {
            max = Integer.MIN_VALUE;
            index = -1;
            // 每轮经过循环找到每轮中最大值
            for (int i1 = 0; i1 < ints.length - i; i1++) {
                // 交换的
                if (ints[i1] >= max) {
                    // 修改值
                    index = i1;
                    max = ints[index];
                }
            }
            // System.out.println("最大值: " + max + ",索引值: " + index);
            // 将最大值放到数组的尾部
            int temp = ints[index];
            ints[index] = ints[ints.length - 1 - i];
            ints[ints.length - 1 - i] = temp;
        }

    }


    /**
     * 冒泡排序的算法实现
     * 交换的使数组的元素有序
     * 时间复杂度: O(n^2)
     *
     * @param ints 需要排序的数组
     */
    public static void bubbleSort(int[] ints) {
        // 冒泡排序算法实现
        int temp;
        boolean flag = true;
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < ints.length - (i + 1); j++) {
                // 交换值
                if (ints[j] > ints[j + 1]) {
                    // 发生过交换
                    flag = false;
                    temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
            }
            // 没有发生交换,直接退出
            if (flag) {
                break;
            } else {
                flag = true;
            }
        }

    }

    /**
     * 冒泡排序的逆运算
     */
    public static int[] bubbleSortReverse(int[] arr) {
        int temp;
        // 进行length - 1轮
        for (int i = 1; i < arr.length; i++) {
            // 进行
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }

            }

        }

        return arr;
    }

}
