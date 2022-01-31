package com.yutianhui.learning.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序实现代码
 *
 * @author yutianhui
 * @date 2021/12/25 14:11
 */
public class RadixSortTest {

    public static void main(String[] args) {
        // 遍历整数的每一位数字
        int num = 1550045;
        System.out.println("生成:" + num);
        StringBuilder strb = new StringBuilder();
        int i = 1;
        int n = 0;
        while (true) {
            n = num / i % 10;
            strb.append(n).append(" ");
            i *= 10;
            if (num / i == 0) break;
        }
        System.out.println("获得:" + strb);

        // --- 下面是验证数组排序的工具
        // 基数排序测试
        int[] ints = {12, 43, 1, 49, 79, 56, 147};
        System.out.println("Before:\t" + Arrays.toString(ints));
        radixSort(ints);
        System.out.println("After:\t" + Arrays.toString(ints));

        // --- 下面是测试基数排序的速度
        int[] makeInts = BubbleAndChoiceSortTest.makeInts(100000);
        long start = System.currentTimeMillis();
        radixSort(makeInts);
        long end = System.currentTimeMillis();
        System.out.println("radixSort(makeInts) 耗费时间: " + (end - start) + "ms");
        System.out.println(String.format("抽样检查: [%s] , [%s] , [%s] , [%s] , [%s]", makeInts[5], makeInts[50], makeInts[5000], makeInts[50000], makeInts[80000]));

    }

    /**
     * 基数排序实现代码 <br>
     * 创建多个数组,通过不断比较元素的每一位进行分类并排序 <br>
     * 数组中的最大位数就是算法执行的轮次数 <br>
     * 基数排序会使用大量的空间 [空间换时间]
     *
     * @param ints 待排序的数组
     */
    public static void radixSort(int[] ints) {
        // 创建10个和ints长度一样的数组
        int[][] bucket = new int[10][ints.length];
        // 保存每个桶存放元素的数量
        int[] bucketCounts = new int[10];
        // 增量
        int i = 1;
        // 不断循环
        while (true) {
            // 记录状态
            int count = 0;
            // 将每个元素的每位数字比较参与,放到bucket,记录数量加加
            // 个位数 -> 十位数 -> 百位数 ....
            for (int index = 0; index < ints.length; index++) {
                // 获取到这一轮的单个数字
                int n = ints[index] / i % 10;
                // 判断是否到达最大位数的数字
                if (ints[index] / i == 0) {
                    count++;
                }
                // 放到对应的bucket中
                bucket[n][bucketCounts[n]] = ints[index];
                // bucketCounts增加
                bucketCounts[n]++;
            }

            // 达到退出条件,最大数字所有位数都已经参与过
            if (count == ints.length) break;

            // 原始数组的索引
            int origin = 0;
            // 将bucket中的存储的数字按照顺序放回到原来的数组中
            for (int index = 0; index < bucket.length; index++) {
                int c = bucketCounts[index];
                for (int j = 0; j < c; j++) {
                    // 填充数字
                    ints[origin] = bucket[index][j];
                    origin++;
                    bucketCounts[index]--;
                }
            }
//            System.out.println("--> " + Arrays.toString(ints));
            // 增加量,向后除
            i *= 10;
        }

    }

}
