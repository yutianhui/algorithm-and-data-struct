package com.yutianhui.learning.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序测试代码
 *
 * @author yutianhui
 * @date 2021/12/24 20:43
 */
public class MergeSortTest {

    public static void main(String[] args) {
        // 创建要排序的数组
        int[] ints = {7, 37, 93, 23, 18, 71, 64, 50};
        // 创建临时数组
        int[] temp = new int[ints.length];
        /*

        7 37 93 23 | 18 71 64 50
        7 37 | 93 23 , 18 71 | 64 50

         */
        System.out.println(String.format("创建的数组: %s", Arrays.toString(ints)));
        mergeSort(ints, 0, ints.length - 1, temp);
        System.out.println(String.format("归并排序: %s", Arrays.toString(ints)));

        // -------下面是速度测试--------
        int[] ints1 = BubbleAndChoiceSortTest.makeInts(100000);
        long start = System.currentTimeMillis();
        mergeSort(ints1, 0, ints1.length - 1, new int[ints1.length]);
        long end = System.currentTimeMillis();
        System.out.println("使用时间: " + (end - start) + "ms");
        System.out.println(String.format("检查: 5.[%s] 50.[%s] 5000.[%s] 50000.[%s]", ints1[5], ints1[50], ints1[5000], ints1[50000]));


    }

    /**
     * 归并排序算法实现 <br>
     * 将总的数据分成小的组,在组内完成排序后再进行合并 <br>
     * 合并的时候需要使用临时数组 [空间换取时间]
     *
     * @param ints  待排序的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param temp  复制数据的临时数组
     */
    public static void mergeSort(int[] ints, int left, int right, int[] temp) {
        // 不满足排序条件直接退出
        if (left >= right) return;
        // 满足排序条件
        int mid = (left + right) / 2;
        // 分解
        mergeSort(ints, left, mid, temp); // 继续分解左边的
        mergeSort(ints, mid + 1, right, temp); // 继续分解右边的
        // 合并分解
        merge(ints, left, mid, right, temp);
    }

    /**
     * 合并的方法,将分解的数组合并为一个有序的数组
     *
     * @param ints  合并的数组
     * @param left  左边的起始索引
     * @param mid   中间索引
     * @param right 右边的末尾索引
     * @param temp  拷贝数据的临时数组
     */
    private static void merge(int[] ints, int left, int mid, int right, int[] temp) {
        // 临时数组的索引
        int index = 0;
        // 记录初始的起始位置
        int init = left;

        // 1. 将数据比较的复制到临时数组中
        int riStart = mid + 1;
        while (left <= mid && riStart <= right) {
            if (ints[left] <= ints[riStart]) {
                // left小,将left放到临时数组
                temp[index] = ints[left];
                // left后移,index++
                left++;
            } else {
                temp[index] = ints[riStart];
                // ristart后移,index++
                riStart++;
            }
            index++;
        }

        // 2. 将左组/右组中剩余的值追加到临时数组中
        // 左组有值
        while (left <= mid) {
            temp[index++] = ints[left++];
        }
        // 右组有值
        while (riStart <= right) {
            temp[index++] = ints[riStart++];
        }

        // 3. 将值拷贝回数组中的位置
        index = 0;
        while (init <= right) {
            ints[init++] = temp[index++];
        }

    }


}
