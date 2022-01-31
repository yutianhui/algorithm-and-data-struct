package com.yutianhui.learning.algorithm.search;

import java.util.Arrays;

/**
 * 斐波那契查找实现
 *
 * @author yutianhui
 * @date 2021/12/27 15:08
 */
public class FibSearchTest {

    public static void main(String[] args) {
        // 待查找的数组
        int[] ints = {12, 12, 23, 45, 45, 45, 78, 78, 89, 89, 89, 95, 95};
        // ---- 测试使用ints进行操作
        System.out.println("length = " + ints.length);
        System.out.println(Arrays.toString(fib(13)));
        System.out.println("原始数组: " + Arrays.toString(ints));
        int[] result = fibSearchMuilt(ints, 12);
        System.out.println("查找结果: " + Arrays.toString(result));

    }

    /**
     * 斐波那契查找算法实现 <br>
     * 借助斐波那契数列的特点,从第三位开始,相邻的数的比值无限接近0.618(黄金分割比) <br>
     *
     * @param arr    有序的数组,升序的
     * @param target 查找的目标
     */
    public static int[] fibSearchMuilt(int[] arr, int target) {
        // 创建对应的数组
        int[] fibs = fib(arr.length);
        // 创建临时数组
        int[] temps = new int[0];
        // 如果传入的数组不满足斐波那契,则补足
        if (arr.length != fibs[fibs.length - 1]) {
            // 初始化临时数组
            temps = new int[fibs[fibs.length - 1]];
            // 复制数组元素
            for (int i = 0; i < fibs[fibs.length - 1]; i++) {
                if (i <= arr.length - 1) {
                    temps[i] = arr[i];
                } else {
                    // 将后面的数字补充为最大的那个数字
                    temps[i] = arr[arr.length - 1];
                }
            }
        }
        // 对temps进行斐波那契搜索
        if (temps.length == 0) {
            // arr满足斐波那契
            temps = arr;
        }
        // 使用temp进行斐波那契查找
        int left = 0;
        int right = temps.length - 1;
        int k = 2;
        int mid;
        int midVal;
        // 循环比较
        while (left <= right) {
            // 找到同一个位置了
            if (left == right && temps[left] == target) {
                return new int[]{left};
            } else if (left == right && temps[left] != target) {
                break;
            }
            // 计算mid和midValue
            mid = left + fibs[(fibs.length - k)] - 1;
            midVal = temps[mid]; // 当前的中间值
            // 将target和midV比较
            if (target < midVal) {
                // 向左边查找
                right = mid;
                k++;
            } else if (target > midVal) {
                // 向右边查找
                left = mid + 1;
                k += 2;
            } else {
                // 找到target,target == midVal
                // 向左,向右查找相同的值并将他们放到同一个数组中返回
                int count = 0; // 目标的个数
                int other = mid; // 创建向左边运动的指针
                // System.out.println("other: " + other);
                // 左边
                while (other >= 0 && temps[other] == midVal) {
                    count++;
                    other--;
                }
                // 使指针指向最左边的target
                other++;
                // mid右移
                mid++;
                // 右边
                while (mid <= temps.length - 1 && temps[mid] == midVal) {
                    count++;
                    mid++;
                }
                // 判断是否在数组范围内
                if (arr.length - 1 >= other) {
                    // 在数组范围内
                    int end = other + count - 1; // 最后一个target的index
                    // 最后一个target的索引超出原数组的边界
                    if (end > arr.length - 1) {
                        count = count - (end - arr.length + 1);
                    }
                    int[] result = new int[count];
                    for (int i = 0; i < count; i++) {
                        result[i] = other + i;
                    }
                    return result;
                }

            }

        }
        // 没有找到,返回空的数组
        return new int[0];
    }

    /**
     * 根据数组的长度返回最近的斐波那契数列
     * 斐波那契数列: 1 1 2 3 5 8 13
     *
     * @param n 数组的长度
     */
    private static int[] fib(int n) {
        if (n == 1) return new int[]{1};
        // 记数
        int count = 3;
        // 前一个和后一个
        int prev = 1;
        int next = 2;
        int sum = 3;
        int temp;
        while (n >= sum) {
            count++;
            sum = prev + next;
            temp = next;
            next = sum;
            prev = temp;
            if (sum >= n) break;
        }
        // 创建真正的数列,count >= 2
        int[] ints = new int[count];
        ints[0] = 1;
        ints[1] = 1;
        // 填充数据
        for (int i = 2; i < ints.length; i++) {
            ints[i] = ints[i - 1] + ints[i - 2];
        }
        return ints;
    }

}
