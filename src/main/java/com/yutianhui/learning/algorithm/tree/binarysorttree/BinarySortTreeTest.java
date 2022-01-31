package com.yutianhui.learning.algorithm.tree.binarysorttree;

import com.yutianhui.learning.algorithm.sort.BubbleAndChoiceSortTest;

/**
 * 二叉排序树测试类
 *
 * @author yutianhui
 * @date 2022/1/5 20:33
 */
public class BinarySortTreeTest {

    public static void main(String[] args) {
        long startAll = System.currentTimeMillis();
        // 创建数组
//        int[] test = {1, 2, 4, 5, 8, 9, 10};
        // 创建数组
        int[] test = BubbleAndChoiceSortTest.makeInts(100000);
        // 创建BST
        long startCreate = System.currentTimeMillis();
        IntegerBinarySortTree binarySortTree = new IntegerBinarySortTree(test);
        long endCreate = System.currentTimeMillis();
        System.out.println("创建树: " + (endCreate - startCreate) + "ms");

        // 添加元素
        long startAdd = System.currentTimeMillis();
        binarySortTree.addInt(45);
        long endAdd = System.currentTimeMillis();
        System.out.println("添加花费时间: " + (endAdd - startAdd) + "ms");

        // -- 查找测试'
        long startSearch = System.currentTimeMillis();
        System.out.println("查找(4): " + binarySortTree.getInt(45));
        long endSearch = System.currentTimeMillis();
        System.out.println("查找花费时间: " + (endSearch - startSearch) + "ms");

        // 删除元素
        long startDel = System.currentTimeMillis();
        binarySortTree.removeInt(45);
        long endDel = System.currentTimeMillis();
        System.out.println("删除花费时间: " + (endDel - startDel) + "ms");

        // 结束计时
        long endAll = System.currentTimeMillis();
        System.out.println("程序用时: " + (endAll - startAll) + "ms");
    }

}
