package com.yutianhui.learning.algorithm.search;

import com.yutianhui.learning.algorithm.sort.BubbleAndChoiceSortTest;

import java.util.Arrays;

/**
 * 二分查找代码测试
 *
 * @author yutianhui
 * @date 2021/12/25 16:12
 */
public class BinarySearchTest {

    public static void main(String[] args) {
        // 查找指定元素
        int[] ints = BubbleAndChoiceSortTest.makeInts(20);
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        System.out.println("(45) index is " + binarySearch(ints, 0, ints.length - 1, 45));

        // 查找多个指定元素
        // 自定义有重复元素的数组
        int[] custom = {2, 2, 2, 2, 5, 6};
        int[] result = binarySearchMuilt(custom, 0, custom.length - 1, 2);
        System.out.println(String.format("(%s) indexs are %s", 2, Arrays.toString(result)));


    }

    /**
     * 二分查找算法实现 <br>
     *
     * @param ints   有序数组,从小到大的
     * @param left   查找的左边起始位置
     * @param right  查找的右边结束位置
     * @param target 要查找的目标
     */
    public static int binarySearch(int[] ints, int left, int right, int target) {
        // 终止条件,没有找到元素
        if (left > right || ints[left] > target || ints[right] < target) return -1;

        // 找到中间值
        int mid = (left + right) / 2;
        int midVal = ints[mid];

        // 比较向左/右递归
        if (target > midVal) {
            // 向右边递归
            return binarySearch(ints, mid + 1, right, target);
        } else if (target < midVal) {
            // 向左递归
            return binarySearch(ints, left, mid - 1, target);
        } else {
            return mid;
        }

    }

    /**
     * 二分查找算法实现 <br>
     *
     * @param ints   有序数组,从小到大的
     * @param left   查找的左边起始位置
     * @param right  查找的右边结束位置
     * @param target 要查找的目标
     */
    public static int[] binarySearchMuilt(int[] ints, int left, int right, int target) {
        // 终止条件,没有找到元素
        if (left > right || ints[left] > target || ints[right] < target) return new int[0];

        // 找到中间值
        int mid = (left + right) / 2;
        int midVal = ints[mid];

        // 比较向左/右递归
        if (target > midVal) {
            // 向右边递归
            return binarySearchMuilt(ints, mid + 1, right, target);
        } else if (target < midVal) {
            // 向左递归
            return binarySearchMuilt(ints, left, mid - 1, target);
        } else {
            // 向左,向右查找相同的值并将他们放到同一个数组中返回
            int count = 0;
            int other = mid; // 其他相同的索引位置
            // 向左查找
            while (other >= left && ints[other] == target) {
                count++;
                other--;
            }
            // 向右查找
            while (mid + 1 <= right && ints[mid + 1] == target) {
                count++;
                mid++;
            }
            // 创建数组
            int[] result = new int[count];
            // 填充数组
            for (int i = 1; i <= result.length; i++) {
                result[i - 1] = other + i;
            }
            return result;
        }

    }

}
