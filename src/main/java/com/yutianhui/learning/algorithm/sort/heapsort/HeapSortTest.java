package com.yutianhui.learning.algorithm.sort.heapsort;

import com.yutianhui.learning.algorithm.MainAlgorithmApp;

import java.util.Random;

/**
 * 堆排序测试
 *
 * @author yutianhui
 * @date 2021/12/30 17:45
 */
public class HeapSortTest {

    public static void main(String[] args) {
        // ------------------
        System.out.println("-------堆排序速度测试-------");
        int[] ints = new int[10000 * 10];
        for (int i = 0; i < ints.length; i++) {
            // 随机数字
            ints[i] = new Random().nextInt(1000);
        }
        Object[] objects = MainAlgorithmApp.methodInvokeTime(arg -> {
            heapSort(ints, 2);
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

        // --测试方法使用的
//        int[] test = {4, 8, 6, 9, 2, 7, 10, 1, 3};
//
//        heapSort(test, 2);


    }


    /**
     * >>>> 堆排序实现代码 (升序) <<<< <br>
     * ----------- <br>
     * 思想: <br>
     * 1.有一个无序的序列,长度 = n <br>
     * 2.不断的构造大顶堆/小顶堆 <br>
     * 3.将大顶堆/小顶堆的root节点(0索引元素)和末尾进行交换 <br>
     * 4.在剩下的元素中继续2,3步,每次排序一个元素到末尾,直到只有一个元素退出 <br>
     * ----------- <br>
     * 堆排序也是选择排序的一种,进行(length - 1)轮
     *
     * @param arr    待排序的无序序列
     * @param status 1使用DTU,2使用UTD
     */
    public static void heapSort(int[] arr, int status) {
        // 长度不符合
        if (arr.length < 2) {
            System.out.println("数组长度为 (" + arr.length + ") ,不需要排序....");
            return;
        }
        // 临时变量,用于交换
        int temp;
        // 循环arr.length - 1遍,使用DTU (从下到上)
        if (status == 1) {
            for (int i = arr.length - 1; i >= 1; i--) {
                // 构建大堆顶
                boolean b = heapRoofDTU(arr, i);
                // 构建完成,将0索引元素交换到i索引
                if (b) {
                    temp = arr[0];
                    arr[0] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        // 使用UTD,从上到下
        else if (status == 2) {
            for (int i = arr.length - 1; i >= 1; i--) {
                // System.out.println("i:" + i);
                boolean b = heapRoofUTD(arr, i);
                if (b) {
                    temp = arr[0];
                    arr[0] = arr[i];
                    arr[i] = temp;
                    // System.out.println("-->" + Arrays.toString(arr));
                }
            }
        } else {
            System.out.println("输入的status有误,请在1和2中选择...");
        }
    }


    /**
     * 构建大顶堆实现 (1) <br>
     * 将指定数组的指定范围(0 - right)构建为大堆顶 <br>
     * 从下到上构建 <br>
     *
     * @param arr   待构建的数组
     * @param right 指定的结束范围
     * @return 构建完成返回true, 失败返回false
     */
    private static boolean heapRoofDTU(int[] arr, int right) {
        // 进行构建
        int last = (right - 1) / 2, limit = last; // 最后一个非叶子节点索引
        // 临时变量,用于交换
        int temp;
        // (非叶子节点的左节点索引),(非叶子节点索引)
        int leftChild, rightChild, parent;
        // 循环两次
        while (Math.abs(last) <= limit) {
            parent = Math.abs(last);
            // 获取左右叶子节点的索引
            leftChild = (2 * parent + 1);
            rightChild = leftChild + 1;
            // 进行构建,比较
            // 没有右边的节点,超出索引,并且左子节点值大于父节点,需要交换
            leftChild = rightChild <= right ? (arr[leftChild] > arr[rightChild] ? leftChild : rightChild) : leftChild;
            if (arr[parent] < arr[leftChild]) {
                // 交换
                temp = arr[parent];
                arr[parent] = arr[leftChild];
                arr[leftChild] = temp;
            }
            // 索引指向下一个非叶子节点
            last--;
        }
        return true;
    }


    /**
     * 构建大顶堆实现 (2) <br>
     * 从上到下进行构建 <br>
     *
     * @param arr   待排序的数组
     * @param right 边界的索引,逐渐的减少到[0,1],从[length-1,1]
     */
    public static boolean heapRoofUTD(int[] arr, int right) {
        // 不满足条件
        if (right <= 0) return false;
        // 非叶子节点的索引和值
        int parent, parentVal;
        // 遍历给定序列的非叶子节点,第一个是(right - 1) / 2,之后减一直到0
        for (int i = (right - 1) / 2; i >= 0; i--) {
            // 赋值索引和非叶子节点值
            parent = i;
            parentVal = arr[parent];
            // 循环的将从下到上的将给定范围的序列调整为大堆顶
            for (int max = 2 * parent + 1; max < right; max = 2 * max + 1) {
                // rightChild索引没有超出right界限,并且rightChild > leftChild
                if (max + 1 <= right && arr[max + 1] > arr[max]) {
                    max++; // 加一,改为rightChild索引
                }
                // 父节点值小于child值
                if (parentVal < arr[max]) {
                    arr[parent] = arr[max];
                    parent = max;
                } else break;
            }
            arr[parent] = parentVal;
        }
        return true;
    }

}
