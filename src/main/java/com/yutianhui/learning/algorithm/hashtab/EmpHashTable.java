package com.yutianhui.learning.algorithm.hashtab;

import com.yutianhui.learning.algorithm.bean.Employee;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * 哈希表实现类
 *
 * @author yutianhui
 * @date 2021/12/28 14:38
 */
public class EmpHashTable {
    // 存放emp链表的数组,初始容量为8条
    private EmpSingleLinkedList[] empLists = new EmpSingleLinkedList[8];
    // 存放empid的数组,记录这放入和取出的empId
    // 不够的话会进行增加
    int[] ids = new int[16];
    // ids索引
    int index = 0;
    // emp数量
    int count = 0;

    // 初始化
    {
        // 初始化链表
        for (int i = 0; i < empLists.length; i++) {
            empLists[i] = new EmpSingleLinkedList();
        }
        // 循环重新赋值
        Arrays.fill(ids, -1);
    }

    // 空参构造器
    public EmpHashTable() {
        // 创建新的employees集合
    }

    // 增加emp,添加到对应的链表中
    public void addEmp(Employee employee) {
        // 满了,数组需要扩容
        if (count >= ids.length) {
            idsIncrement();
        }
        // 获取散列的结果,添加到对应的链表中
        int hashInt = hashInt(employee.getId());
        EmpSingleLinkedList list = empLists[hashInt];
        list.addEmp(employee);
        ids[index++] = employee.getId();
        count++;
    }

    // 根据id删除对应的emp
    public boolean delEmp(Integer id) {
        // 没有值
        if (count == 0 || id < 0) return false;
        // id在ids中的索引
        int i = -1;
        for (int i1 = 0; i1 < ids.length; i1++) {
            if (ids[i1] == id) i = i1;
        }
        if (i == -1) return false;
        // 在链表中删除
        boolean delEmp = empLists[hashInt(id)].delEmp(id);
        if (delEmp) {
            // 删除掉了
            count--;
            // 将索引
            while (i < index) {
                if (i + 1 > ids.length - 1) {
//                    System.out.println(String.format("ids[%s] = (%s) | ids[%s] = (%s)", i, ids[i], i, -1));
                    ids[i] = -1;
                } else {
                    ids[i] = ids[i + 1];
                }
                i++;
            }
            index--;
        }
        return delEmp;
    }

    // 根据id获取emp
    public Employee getEmp(Integer id) {
        // 没有元素
        if (count == 0) return null;
        // 有元素
        return empLists[hashInt(id)].getEmp(id);
    }

    // 遍历各个链表的情况
    public void echoEmpLists() {
        System.out.println("--------echoEmpLists--------");
        for (int i = 0; i < empLists.length; i++) {
            System.out.println(String.format("(%s) --> count: %s", i, empLists[i].getCount()));
        }
        System.out.println("总数量:" + count);
        System.out.println("----------------------------");
    }

    // 遍历全部的元素
    public void forEach(BiConsumer<Integer, Employee> biConsumer) {
        // 循环遍历
        for (int i = 0; i < count; i++) {
            // 获取存放的id
            int id = ids[i];
            if (id == -1) {
                continue;
            }
            Employee emp = empLists[hashInt(id)].getEmp(id);
            biConsumer.accept(id, emp);
        }
    }

    // 数组扩容
    public void idsIncrement() {
        // 增加容量
        int cap = (int) (ids.length * 1.5);
        // 复制值
        int[] temps = new int[cap];
        Arrays.fill(temps, -1);
        for (int i = 0; i < ids.length; i++) {
            temps[i] = ids[i];
        }
        System.out.println(String.format("数组扩容(.5): originLength=(%s) , now=(%s)", ids.length, temps.length));
        ids = temps;
    }

    // 散列函数
    // 根据empId进行取模散列
    public int hashInt(Integer id) {
        return id % empLists.length;
    }

}
