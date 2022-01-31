package com.yutianhui.learning.algorithm;

import com.yutianhui.learning.algorithm.sort.BubbleAndChoiceSortTest;

import java.util.function.Function;

import static com.yutianhui.learning.algorithm.sort.heapsort.HeapSortFinalTest.heapSort;

/**
 * 主要的启动类
 *
 * @author yutianhui
 * @date 2021/12/10 13:03
 */
public class MainAlgorithmApp {

    public static void main(String[] args) {
        int[] ints = BubbleAndChoiceSortTest.makeInts(1000000);
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

    }


    public static int test(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }


    /**
     * 函数调用时间测试
     *
     * @param methods 实现调用的方法
     * @param args    调用方法需要的参数
     */
    public static Object[] methodInvokeTime(Function<Object[], Object> methods, Object[] args) {
        // 返回执行时间和函数执行结果
        Object[] results = new Object[2];
        long start = System.currentTimeMillis();
        Object result = methods.apply(args);
        long end = System.currentTimeMillis();
        results[0] = end - start;
        results[1] = result;
        return results;
    }

}
