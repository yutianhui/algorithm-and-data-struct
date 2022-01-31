package com.yutianhui.learning.algorithm.hashtab;

import com.yutianhui.learning.algorithm.bean.Employee;

import java.util.Random;
import java.util.UUID;

/**
 * 使用数组加链表实现哈希表结构
 *
 * @author yutianhui
 * @date 2021/12/28 14:26
 */
public class HashTableTest {

    public static void main(String[] args) {
        // 链表测试
//        EmpSingleLinkedList empSingleLinkedList = new EmpSingleLinkedList();
//        empSingleLinkedList.addEmp(new Employee(12, "zhangSan", 4500, "xi'an"));
//        empSingleLinkedList.addEmp(new Employee(13, "gSan", 4102, "shan'xi"));
//        empSingleLinkedList.addEmp(new Employee(14, "zhan", 7852, "xian'yang"));
//        empSingleLinkedList.addEmp(new Employee(15, "hanan", 7426, "jing'xi"));
//        empSingleLinkedList.addEmp(new Employee(16, "hanan", 77526, "jing'xi"));
//        empSingleLinkedList.addEmp(new Employee(17, "hanan", 7536, "jianhg'xi"));
//        empSingleLinkedList.echoAll();
//        System.out.println(empSingleLinkedList.delEmp(17));
//        System.out.println(empSingleLinkedList.delEmp(14));
//        empSingleLinkedList.echoAll();
//        System.out.println(empSingleLinkedList.getCount());
//        System.out.println(empSingleLinkedList.getEmp(14));

        // 哈希表测试
        EmpHashTable empHashTable = new EmpHashTable();
        for (int i = 0; i < 16; i++) {
            empHashTable.addEmp(new Employee(i, UUID.randomUUID().toString(), new Random().nextInt(145000), new Random().nextInt(145000) + "sg" + new Random().nextInt(145000) + "abdEY"));
        }

        // 循环遍历
        empHashTable.forEach((id, emp) -> {
            System.out.println(String.format("%s -> %s", id, emp));
        });
        boolean b = empHashTable.delEmp(15);
        System.out.println(b);
        empHashTable.forEach((id, emp) -> {
            System.out.println(String.format("%s -> %s", id, emp));
        });

        empHashTable.echoEmpLists();
        System.out.println("getEmp(12) : " + empHashTable.getEmp(12));


    }


}
