package com.yutianhui.learning.algorithm.stack;

import java.util.Scanner;

/**
 * TODO
 *
 * @author yutianhui
 * @date 2021/12/10 17:50
 */
public class ArrayStackTest {

    public static void main(String[] args) {
        // 测试数组模拟栈
        Scanner scanner = new Scanner(System.in);
        // 创建栈
        ArrayStack arrayStack = new ArrayStack(5);
        // 循环菜单
        menu:
        while (true) {
            System.out.println("---- 菜单 ----");
            System.out.println("push\t=>\t入栈");
            System.out.println("pop\t\t=>\t出栈");
            System.out.println("show\t=>\t显示栈的信息");
            System.out.println("exit\t=>\t退出");
            System.out.println("---- 结束 ----");
            System.out.print("请选择: ");
            String command = scanner.nextLine();
            switch (command) {
                case "push":
                    System.out.print("请输入字符串:");
                    String temp = scanner.nextLine();
                    arrayStack.push(temp);
                    System.out.println("添加完成...");
                    break;
                case "pop":
                    System.out.printf("弹出的元素是:[%s] \n", arrayStack.pop());
                    break;
                case "show":
                    arrayStack.show();
                    break;
                case "exit":
                    System.out.println("#### 结束程序运行 ####");
                    break menu;
                default:
                    System.out.println("你没有选择...[无效]");
            }

        }

    }


}

class ArrayStack {

    private String[] datas; //栈的数据
    private int head = -1; //栈顶
    private int count = 0; //栈数据量
    private int maxSize = 0;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.datas = new String[maxSize];
    }

    // 添加元素
    public void push(String string) {
        if (isFull()) {
            System.out.println("栈达到最大数量...");
            return;
        }
        datas[++head] = string;
        count++;
    }

    // 弹出新的元素
    public String pop() {
        if (isEmpty()) {
            System.out.println("栈是空的了...");
            return null;
        }
        String result = datas[head];
        datas[head] = null;
        head--;
        count--;
        return result;
    }

    // 展示show()
    public void show() {
        if (isEmpty()) {
            System.out.println("栈是空的....");
            return;
        }
        System.out.println("<< 栈内数据如下");
        for (int i = count; i > 0; i--) {
            System.out.printf("[%d] == <%s> \n", i - 1, datas[i - 1]);
        }
        System.out.println("栈元素数量: " + count + ", 栈顶索引为: " + head);
        System.out.println("展示数据完成 >>");
    }


    public boolean isEmpty() {
        return head == -1;
    }

    // 判断是否已经满了
    public Boolean isFull() {
        return head == maxSize - 1;
    }

    // 获取栈顶指针
    public int getHead() {
        return head;
    }

    public int getMaxSize() {
        return maxSize;
    }

    // 获取栈中的数据
    public int getCount() {
        return count;
    }
}
