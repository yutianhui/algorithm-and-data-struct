package com.yutianhui.learning.algorithm.sort;

import java.util.Random;

/**
 * 插入排序实现测试代码
 *
 * @author yutianhui
 * @date 2021/12/23 15:53
 */
public class InsertSortTest {

    public static void main(String[] args) {
        // 测试 1
        int[] ints = new int[100000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(50000);
        }
        // 使用插入排序
        long one = System.currentTimeMillis();
        insertSort(ints);
        long two = System.currentTimeMillis();
        System.out.println("insertSort(ints) 花费时间: " + (two - one) + "ms");
        System.out.println(String.format("抽样检查,5.[%s] 500.[%s] 5000.[%s] 50000.[%s]", ints[5], ints[500], ints[5000], ints[50000]));


        // 测试 2
        int[] ints1 = new int[100000];
        for (int i = 0; i < ints1.length; i++) {
            ints1[i] = new Random().nextInt(50000);
        }
        long three = System.currentTimeMillis();
        insertSortWithWhile(ints1);
        long four = System.currentTimeMillis();
        System.out.println("insertSortWithWhile(ints) 花费时间: " + (four - three) + "ms");
        System.out.println(String.format("抽样检查,5.[%s] 500.[%s] 5000.[%s] 50000.[%s]", ints1[5], ints1[500], ints1[5000], ints1[50000]));

    }

    /**
     * 插入排序实现2,使用while循环
     *
     * @param ints 待排序的数组
     */
    public static void insertSortWithWhile(int[] ints) {
        int value; // 将要插入的值
        int index; // 要插入的索引位置
        // 循环n - 1次
        for (int i = 1; i < ints.length; i++) {
            value = ints[i]; // 赋值变量
            index = i - 1;
            // 和后面的交换位置
            while (index >= 0 && ints[index] > value) {
                ints[index + 1] = ints[index];
                index--;
            }
            // 修改值
            ints[index + 1] = value;
        }

    }


    /**
     * 插入排序测试代码,将前面的元素看作有序集合,将后面的元素插入到前面的有序列表
     * 数组元素为 n
     * 执行次数 n - 1
     *
     * @param ints 待排序的数组
     */
    public static void insertSort(int[] ints) {
        // 循环ints.length - 1次
        int insertIndex; // 要插入的索引位置
        int temp; // 临时变量,用于交换变量
        for (int i = 1; i < ints.length; i++) {
            insertIndex = i;
            for (int i1 = 0; i1 < i; i1++) {
                if (ints[i1] > ints[i]) {
                    // 要插入的索引位置
                    insertIndex = i1;
                    break;
                }
            }
            // 如果后面的比前面的都大,不用交换位置
            if (insertIndex == i) {
//                System.out.println(String.format("---> 第%s/%s次 | %s", i, ints.length, Arrays.toString(ints)));
                continue;
            }
            // 更换位置
            for (int j = i; j > insertIndex; j--) {
                temp = ints[j];
                ints[j] = ints[j - 1];
                ints[j - 1] = temp;
            }
//            System.out.println(String.format("---> 第%s/%s次 | %s", i, ints.length, Arrays.toString(ints)));
        }

    }

}
