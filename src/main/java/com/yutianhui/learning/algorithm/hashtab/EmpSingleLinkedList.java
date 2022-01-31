package com.yutianhui.learning.algorithm.hashtab;

import com.yutianhui.learning.algorithm.bean.Employee;

/**
 * 存储Person的单向链表
 *
 * @author yutianhui
 * @date 2021/12/28 14:25
 */
public class EmpSingleLinkedList {
    // 第一个节点
    private Node head;
    // 最后一个节点
    private Node footer;
    // 存储的元素个数
    private Integer count = 0;

    public Integer getCount() {
        return count;
    }

    // 创建链表的构造器
    public EmpSingleLinkedList() {

    }

    // 给链表添加员工
    public void addEmp(Employee employee) {
        Node node = new Node(employee, null);
        if (count == 0) {
            // 第一次添加
            head = node;
            footer = node;
        } else {
            // 不是第一次
            footer.setNext(node);
            footer = node;
        }
        // 数量加一
        count++;
    }

    // 删除员工的方法
    public boolean delEmp(Integer id) {
        // 链表空的
        if (count == 0) {
            return false;
        }
        // 声明变量
        Node curr = head;
        // 只有一个元素,并且这个元素不是要删除的
        if (count == 1 && curr.getCurr().getId() != id) {
            return false;
        }
        // 第一个就是要删除的元素
        if (curr.getCurr().getId() == id) {
            curr = curr.getNext();
            head = curr;
            count--;
            return true;
        }
        // 第一个元素不是要删除的元素
        // 设置一个要删除元素的前一个元素
        Node prev = head;
        // 将curr设置到第二个节点
        curr = curr.getNext();
        // 查找
        while (curr != null) {
            if (curr.getCurr().getId() == id) {
                prev.setNext(curr.getNext());
                count--; // 数量减少
                return true;
            }
            // 切换到下一个
            curr = curr.getNext();
            prev = prev.getNext();
        }
        return false;
    }

    // 根据id查找对应的employee
    public Employee getEmp(Integer id) {
        if (count == 0) return null;
        Node curr = head;
        while (curr != null) {
            if (curr.getCurr().getId() == id) return curr.getCurr();
            curr = curr.getNext();
        }
        return null;
    }

    // 遍历展示employees
    public void echoAll() {
        String format = "--------%s--------";
        if (count == 0) {
            System.out.println(String.format(format, "没有数据"));
            return;
        }
        // 有数据
        System.out.println(String.format(format, "echoAll (EmpSingleLinkedList)"));
        // 输出
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.getCurr());
            curr = curr.getNext();
        }
        System.out.println(String.format(format, "输出完毕"));
    }

    // 链表节点实现类
    private static class Node {
        // 当前节点存储的员工
        private Employee curr;
        // 下一个节点
        private Node next;

        public Node(Employee curr, Node next) {
            this.curr = curr;
            this.next = next;
        }

        public Employee getCurr() {
            return curr;
        }

        public void setCurr(Employee curr) {
            this.curr = curr;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
