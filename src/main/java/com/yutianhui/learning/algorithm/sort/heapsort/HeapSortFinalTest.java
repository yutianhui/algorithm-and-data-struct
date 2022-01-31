package com.yutianhui.learning.algorithm.sort.heapsort;

import com.yutianhui.learning.algorithm.MainAlgorithmApp;

import java.util.Random;

/**
 * 堆排序的最终版
 *
 * @author yutianhui
 * @date 2021/12/31 17:20
 */
public class HeapSortFinalTest {

    public static void main(String[] args) {
        System.out.println("-------堆排序速度测试-------");
        int[] ints = new int[10000 * 10];
        for (int i = 0; i < ints.length; i++) {
            // 随机数字
            ints[i] = new Random().nextInt(50000);
        }
        Object[] objects = MainAlgorithmApp.methodInvokeTime(arg -> {
            heapSort(ints);
            return null;
        }, null);
        System.out.println(String.format("耗费时间: (%s)ms", objects[0]));
        // 使用i的值
        int i = (ints.length - 1);
        // 输出语句
        System.out.println(String.format("抽样检查: %s.[%s] , %s.[%s] , %s.[%s] , %s.[%s] , %s.[%s]",
                i / 8, ints[i / 8],
                i * 2 / 8, ints[i * 2 / 8],
                i * 4 / 8, ints[i * 4 / 8],
                i * 6 / 8, ints[i * 6 / 8],
                i, ints[i]
        ));

//        int[] test = {4, 8, 6, 9, 2, 7, 10, 1, 3};
//
//        // buildMaxHeap(test);
//        heapSort(test);
//
//        System.out.println(Arrays.toString(test));


    }

    /**
     * 堆排序代码实现 <br>
     * 思想: <br>
     * 1. 将给的初始数组构建为大顶堆 <br>
     * 2. 交换0号索引元素和最后一个 <br>
     * 3. 继续调整为大顶堆,继续交换,直到元素为一个的时候停止 <br>
     *
     * @param array 待排序的初始数组
     */
    public static void heapSort(int[] array) {
        // 1. 将序列构建为大顶堆
        buildMaxHeap(array);
        // 2. 循环调整和交换0索引和最后索引的值
        // 临时变量,用来交换值
        int temp;
        for (int i = array.length - 1; i >= 1; i--) {
            // 交换0索引和i索引的值
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            // 调整为大顶堆
            heapRoof(array, 0, i - 1);
        }
    }

    /**
     * 构建大顶堆 <br>
     * 将传入的数组构建为大顶堆 <br>
     * 调用的是heapRoof进行调整 <br>
     *
     * @param array 待构建的初始数组
     */
    private static void buildMaxHeap(int[] array) {
        // 循环数组的非叶子节点进行构建
        // 起始为树的最后一个非叶子节点,(arr.length - 2) / 2
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            heapRoof(array, i, array.length - 1);
        }
    }

    /**
     * 根据给定的父节点从上到下的调整为大顶堆 <br>
     *
     * @param array  待调整的数组
     * @param parent 给定的父节点
     * @param right  给定的处理边界
     */
    private static void heapRoof(int[] array, int parent, int right) {
        if (right < 1) return;
        // 存储父节点的值
        int parentVal = array[parent];
        // 循环调整,起始为parent的左节点
        for (int max = 2 * parent + 1; max <= right; max = 2 * max + 1) {
            // parent右节点存在在边界内,并且大于左节点
            if (max + 1 <= right && array[max + 1] > array[max]) {
                max++;
            }
            // 子节点中有大于parent节点的,进行移动
            if (parentVal < array[max]) {
                array[parent] = array[max];
                parent = max;
            } else break;
        }
        // 设置值
        array[parent] = parentVal;
    }

}
