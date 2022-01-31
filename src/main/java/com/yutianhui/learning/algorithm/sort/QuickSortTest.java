package com.yutianhui.learning.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序测试
 *
 * @author yutianhui
 * @date 2021/12/24 15:43
 */
public class QuickSortTest {

    public static void main(String[] args) {
        // length = 7
        int[] ints = {12, 45, 21, 78, 89, 65, 23};
        System.out.println(String.format("原始数组: %s , length=(%s)", Arrays.toString(ints), ints.length));
        quickSort(ints, 0, ints.length - 1);
        System.out.println(String.format("原始数组: %s , length=(%s)", Arrays.toString(ints), ints.length));

        // ==========测试快速排序===========
        int[] ints1 = BubbleAndChoiceSortTest.makeInts(100000);
        long start = System.currentTimeMillis();
        quickSort(ints1, 0, ints1.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("bubbleSort(ints1) 花费时间: " + (end - start) + "ms");
        System.out.println(String.format("抽样检查,[%s] [%s] [%s] [%s]", ints1[5], ints1[500], ints1[5000], ints1[50000]));

    }

    /**
     * 使用快速排序进行数据排序 <br>
     * 左右两边的数据趋近有序 <br>
     * ------------------- <br>
     * [递归操作]
     *
     * @param arr   待排序的数组
     * @param left  左边起始索引
     * @param right 数组length - 1
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让比pivot 值小放到左边
        //比pivot 值大放到右边
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) {
                r--;
            }
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }


}
