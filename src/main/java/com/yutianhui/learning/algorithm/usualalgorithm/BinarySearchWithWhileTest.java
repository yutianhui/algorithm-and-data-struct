package com.yutianhui.learning.algorithm.usualalgorithm;

import java.util.Arrays;

/**
 * 二分查找非递归实现
 *
 * @author yutianhui
 * @date 2022/1/8 17:35
 */
public class BinarySearchWithWhileTest {

    public static void main(String[] args) {
        // 测试
        int[] ints = {12, 45, 56, 89, 78, 12, 45, 52, 12, 20, 4};
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        int[] result = binarySearchMulitWithWhile(ints, 12);
        System.out.println(Arrays.toString(result));

    }

    /**
     * 二分查找非递归实现 <br>
     *
     * @param array  使用的数组,升序的
     * @param target 查找目标
     * @return 返回全部符合的索引数组
     */
    public static int[] binarySearchMulitWithWhile(int[] array, int target) {
        // 起始的左右边界
        int left = 0;
        int right = array.length - 1;
        // 中点
        int mid;
        // 结果的个数
        int count = 0;
        // 循环搜索
        while (left <= right) {
            // 计算中点
            mid = (left + right) / 2;
            if (target < array[mid]) {
                // 向左边找
                right = mid - 1;
            } else if (target > array[mid]) {
                // 向右边找
                left = mid + 1;
            } else {
                // 找到了对应的索引
                left = mid + 1;
                // 向左边找
                while (mid >= 0 && array[mid] == target) {
                    mid--;
                    count++;
                }
                // 向右边找
                while (left <= right && array[left] == target) {
                    left++;
                    count++;
                }
                // 创建数组
                int[] result = new int[count];
                // 存入数据
                for (int i = 0; i < count; i++) {
                    result[i] = mid + 1 + i;
                }
                return result;
            }

        }
        return new int[0];
    }

}
