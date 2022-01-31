package com.yutianhui.learning.algorithm.search;

import com.yutianhui.learning.algorithm.sort.BubbleAndChoiceSortTest;

import java.util.Arrays;

/**
 * 线性查找测试
 *
 * @author yutianhui
 * @date 2021/12/25 16:25
 */
public class LinearSearchTest {

    public static void main(String[] args) {
        int[] ints = BubbleAndChoiceSortTest.makeInts(20);
        System.out.println("Origin:\t" + Arrays.toString(ints));
        System.out.println(linearSearch(ints, 58));

        // ----------
        System.out.println("测试搜索的效率");

    }

    /**
     * 线性查找,在数组中查找指定的元素
     * 查找到则返回元素的索引
     * 否则返回 -1
     *
     * @param ints   要查找的数组
     * @param target 要查找的目标
     * @return 返回target的索引, 没有返回-1
     */
    public static int linearSearch(int[] ints, int target) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
