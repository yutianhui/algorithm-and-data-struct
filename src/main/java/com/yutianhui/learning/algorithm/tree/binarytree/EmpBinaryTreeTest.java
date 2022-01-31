package com.yutianhui.learning.algorithm.tree.binarytree;

import com.yutianhui.learning.algorithm.bean.Employee;

import java.util.Random;
import java.util.UUID;

/**
 * 二叉树的测试类
 *
 * @author yutianhui
 * @date 2021/12/29 22:40
 */
public class EmpBinaryTreeTest {

    public static void main(String[] args) {
        /*
         * 树的测试方法
         * */
        EmpBinaryTree empBinaryTree = new EmpBinaryTree();
        for (int i = 0; i < 10; i++) {
            empBinaryTree.addNode(new Employee(i, UUID.randomUUID().toString().substring(0, 7), new Random().nextInt(14500), new Random().nextInt(14500) + "hjv" + new Random().nextInt(500) + "rj"));
        }
        System.out.println("height: " + empBinaryTree.height());
        System.out.println("root: " + empBinaryTree.getRoot());
        System.out.println(">>>> 测试 <<<<");
        empBinaryTree.getEmpById(4, 1);
        empBinaryTree.delNode(4);
        empBinaryTree.getEmpById(4, 1);


    }

}
