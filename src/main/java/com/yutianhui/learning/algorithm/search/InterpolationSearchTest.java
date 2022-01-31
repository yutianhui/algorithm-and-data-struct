package com.yutianhui.learning.algorithm.search;

import com.yutianhui.learning.algorithm.sort.BubbleAndChoiceSortTest;

import java.util.Arrays;

/**
 * 插值查找测试算法
 *
 * @author yutianhui
 * @date 2021/12/27 14:52
 */
public class InterpolationSearchTest {

    public static void main(String[] args) {
        int[] ints = {4, 4, 4, 5, 7, 9};
        int[] result = interpolationSearchMuilt(ints, 0, ints.length - 1, 4);
        System.out.println(Arrays.toString(result));

        // -- 测试多的
        System.out.println("-----------");
        int[] ints1 = BubbleAndChoiceSortTest.makeInts(1000000);
        Arrays.sort(ints1);
        int[] ints2 = interpolationSearchMuilt(ints1, 0, ints1.length - 1, 75);
        System.out.println(Arrays.toString(ints2));


    }

    /**
     * 插值查找算法实现
     * 和二分查找相比就是改变了mid的计算公式
     * 二分查找: (left + right) / 2 = left + (right - left) * 0.5
     * 插值查找: left + (right - left) * [ (target - ints[left]) / (ints[right] - ints[left]) ]
     *
     * @param ints   有序数组
     * @param left   左边界
     * @param right  右边界
     * @param target 查找目标
     */
    public static int[] interpolationSearchMuilt(int[] ints, int left, int right, int target) {
        // 终止条件,没有找到元素
        if (left > right || ints[left] > target || ints[right] < target) return new int[0];
        // 找到中间值
        int mid = left + (right - left) * (target - ints[left]) / (ints[right] - ints[left]);
        // 输出查看
        String format = "调用... left=(%s),right=(%s),ints[left]=(%s),ints[right]=(%s),mid=(%s)";
        System.out.println(String.format(format, left, right, ints[left], ints[right], mid));
        // 取值
        int midVal = ints[mid];


        // 比较向左/右递归
        if (target > midVal) {
            // 向右边递归
            return interpolationSearchMuilt(ints, mid + 1, right, target);
        } else if (target < midVal) {
            // 向左递归
            return interpolationSearchMuilt(ints, left, mid - 1, target);
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
