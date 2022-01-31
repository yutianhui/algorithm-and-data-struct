package com.yutianhui.learning.algorithm.linkedlist;

import com.yutianhui.learning.algorithm.bean.Person;

/**
 * TODO
 *
 * @author yutianhui
 * @date 2021/12/10 13:33
 */
public class RoundSingleLinkedListTest {

    public static void main(String[] args) {

        /*
         * 单向环形链表测试
         * */
        RoundSingleLinkedList roundSingleLinkedList = new RoundSingleLinkedList();
        roundSingleLinkedList.add(new Person("A", 45, 1L));
        roundSingleLinkedList.add(new Person("B", 78, 2L));
        roundSingleLinkedList.add(new Person("C", 15, 3L));
        roundSingleLinkedList.add(new Person("D", 26, 4L));
        roundSingleLinkedList.add(new Person("E", 74, 5L));
        roundSingleLinkedList.add(new Person("F", 52, 6L));

        roundSingleLinkedList.forEach();

        roundSingleLinkedList.josephu(2, 3);

        roundSingleLinkedList.forEach();

    }

}

// 单向环形链表
class RoundSingleLinkedList {

    // 记录的信息
    private Node first;
    private Node last;
    private Integer count = 0;


    /**
     * josephu问题的解决方法
     *
     * @param startNumber 起始报数人的号码
     * @param number      报数多少
     */
    public void josephu(int startNumber, int number) {
        // 验证参数
        if (startNumber > count || startNumber <= 0 || number <= 0) {
            System.out.println("参数不合法...");
            return;
        }
        // 参数合法进行操作
        int pre = startNumber - 2;
        if (startNumber == 1) {
            pre = count - 1;
        }
        Node preNode = getNode(pre);
        Node node = getNode(startNumber - 1);
        while (true) {
            // 如果只有一个,直接弹出
            if (count == 1) {
                System.out.println("链表弹出:" + node.getPerson() + " [successful] -- 链表清空");
                first = null;
                last = null;
                count = 0;
                break;
            }
            for (int i = 0; i < number - 1; i++) {
                node = node.getNext();
                preNode = preNode.getNext();
            }
            // 脱出链表
            preNode.setNext(node.getNext());
            // 输出信息
            System.out.println("链表弹出:" + node.getPerson());
            node = node.getNext();
            count--;
        }

    }


    // 增加的方法
    public void add(Person person) {
        Node temp = null;
        if (count == 0) {
            temp = new Node(null, person);
            temp.setNext(temp);
            first = temp;
        } else {
            temp = new Node(first, person);
            last.setNext(temp);
        }
        last = temp;
        count++;
    }

    // 删除的方法,提供索引
    public void remove(Integer index) {
        // 尝试获取到节点信息
        Node node = getNode(index);
        if (node == null) return;
        // 存在这个元素
        if (first == node) {
            // 只有这一个元素,重置元素
            first = null;
            last = null;
            count = 0;
        }
        // 找出前一个元素
        Node preNode = getNode(index - 1);
        preNode.setNext(node.getNext());
        count--;
    }

    // 获取指定索引的元素
    public Person get(Integer index) {
        return getNode(index).getPerson();
    }

    // 获取指定索引的元素
    private Node getNode(Integer index) {
        if (index >= 0 && index <= count - 1) {
            // 获取第一个或者最后一个直接返回
            if (index == 0) return first;
            if (index == count - 1) return last;
            // 获取中间的
            Node temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp;
        }
        System.out.println("索引超出....");
        return null;
    }

    // 遍历查看
    public void forEach() {
        if (count == 0) {
            System.out.println("链表中没有数据,是空的.....");
            return;
        }
        // 不是空的
        System.out.println("----- 数据如下 -----");
        Node temp = first;
        for (int i = 0; i < count; i++) {
            // 循环打印
            System.out.println(String.format("%s --> %s", i, temp.getPerson().toString()));
            temp = temp.getNext();
        }
        System.out.println("----- 输出完成 -----");
    }

    public Integer getCount() {
        return count;
    }

    // 节点
    private static class Node {
        // 下一个节点
        private Node next;
        // 节点中的信息
        private Person person;

        public Node(Node next, Person person) {
            this.next = next;
            this.person = person;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Person getPerson() {
            return person;
        }
    }

}