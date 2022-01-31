package com.yutianhui.learning.algorithm.tree.binarytree;

import com.yutianhui.learning.algorithm.bean.Employee;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * 存储特定元素(Employee)的简单二叉树实现
 *
 * @author yutianhui
 * @date 2021/12/29 15:15
 */
public class EmpBinaryTree {

    // 记录的根节点
    private EmpNode root;

    // 记录存放的node,常量的
    private final ArrayList<EmpNode> nodes = new ArrayList<>();

    // 构造器
    public EmpBinaryTree() {

    }

    /**
     * 添加节点 <br>
     * 1. 如果为空,直接设置为root <br>
     * 2. 如果左节点是null,设置到左节点
     *
     * @param value 存放的值
     */
    public void addNode(Employee value) {
        // 创建node
        EmpNode node = new EmpNode(value);
        nodes.add(node);
        // 判断是否为空的
        // 是空的,将node设置为root
        if (nodes.size() == 1) {
            this.root = node;
            return;
        }
        // 不是空的
        // 找到最后放入节点的父节点
        int fatherIndex = (nodes.size() - 2) / 2;
        EmpNode father = nodes.get(fatherIndex);
        if (father.getRightNode() != null) {
            nodes.get(fatherIndex + 1).setLeftNode(node);
        } else if (father.getLeftNode() != null) {
            father.setRightNode(node);
        } else {
            father.setLeftNode(node);
        }
    }

    /**
     * 根据id删除对应的节点 <br>
     * 1.找到id对应的位置,并且删除
     * 2.将全部list中的node的left和right置空
     * 3.使用剩下的节点,重新组成tree
     *
     * @param id 根据empId删除对应的节点
     */
    public boolean delNode(Integer id) {
        // 树中没有元素
        if (getCount() < 1) {
            System.out.println("二叉树是空的,删除失败....id: " + id);
            return false;
        }
        // 1.找到id对应的位置,并且删除
        int index = -1;
        while (index < nodes.size()) {
            index++;
            if (nodes.get(index).getCurrVal().getId() == id) {
                // 找到对应值
                // 删除
                nodes.remove(index);
                // 退出循环
                break;
            }
        }
        if (index == nodes.size()) {
            System.out.println("没有找到要删除的元素...id:" + id);
            return false;
        }
        // 3.使用剩下的节点,重新组成tree
        root = nodes.get(0); // 使用第一个设置为root节点
        // 遍历
        for (int i = 0; i < nodes.size(); i++) {
            // 设置节点
            EmpNode empNode = nodes.get(i);
            empNode.setLeftNode(null);
            empNode.setRightNode(null);
            // 如果是第一个过掉
            if (i == 0) continue;
            // 设置树
            // (1) 获取到最后添加元素的父节点
            int parentIndex = (i - 2) / 2;
            // (2) 判断添加到哪里
            EmpNode parent = nodes.get(parentIndex);
            // (3) 重新设置 left 和 right
            if (parent.getRightNode() != null) {
                nodes.get(parentIndex + 1).setLeftNode(empNode);
            } else if (parent.getLeftNode() != null) {
                parent.setRightNode(empNode);
            } else {
                parent.setLeftNode(empNode);
            }

        }
        return true;
    }

    /**
     * 使用id进行查找二叉树的节点 <br>
     * 遍历(前序/中序/后序),查找到返回即可
     *
     * @param id     要搜索的id
     * @param status 要使用的遍历方式,1 -> 前序,2 -> 中序,3 -> 后序
     */
    public Employee getEmpById(Integer id, int status) {
        if (status > 4 || status < 0) System.out.println("输入的status有误,匹配失败,可选 [1 , 2 , 3]...");
        // 定义返回值
        AtomicReference<Employee> result = new AtomicReference<>(null);
        Consumer<EmpNode> consumer = node -> {
            if (node != null && node.getCurrVal().getId() == id) {
                result.set(node.getCurrVal());
//                System.out.println(node);
            }
        };
        // 查找
        if (status == 1) {
            // 使用前序
            this.root.preEchoAll(consumer);
        } else if (status == 2) {
            // 使用中序
            this.root.currEchoAll(consumer);
        } else if (status == 3) {
            // 使用后序
            this.root.afterEchoAll(consumer);
        }
        return result.get();
    }


    /**
     * 获取树的高度 k <br>
     * 树的高度和树的节点数的关系: 节点数 = 2^k - 1
     */
    public int height() {
        return height(this.root);
    }

    /**
     * 获取以某个节点为根节点的树高度 k
     *
     * @param node 根节点
     */
    private int height(EmpNode node) {
        if (node == null) return 0;
        int left = height(node.getLeftNode());
        int right = height(node.getRightNode());
        return left > right ? (left + 1) : (right + 1);
    }

    /**
     * 遍历树
     *
     * @param employeeConsumer 消费函数型接口实现
     * @param status           使用的遍历方法
     */
    public void echoAllNode(Consumer<Employee> employeeConsumer, int status) {
        if (status > 4 || status < 0) System.out.println("输入的status有误,匹配失败,可选 [1 , 2 , 3]...");
        Consumer<EmpNode> consumer = empNode -> {
            employeeConsumer.accept(empNode.currVal);
        };
        if (status == 1) {
            this.root.preEchoAll(consumer);
        } else if (status == 2) {
            this.root.currEchoAll(consumer);
        } else if (status == 3) {
            this.root.afterEchoAll(consumer);
        }
    }


    // --- getter ---
    public EmpNode getRoot() {
        return root;
    }

    public int getCount() {
        return nodes.size();
    }

    // --- setter ---
    private void setRoot(EmpNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "EmpBinaryTree{" +
                "root=" + root +
                ", count=" + nodes.size() +
                '}';
    }

    /**
     * 二叉树节点 <br>
     * 每个节点存储的数据: 1.当前节点的值(权) 2.leftNode 3.rightNode
     */
    private class EmpNode {
        // 当前节点的权
        private Employee currVal;
        // 左边的节点
        private EmpNode leftNode;
        // 右边的节点
        private EmpNode rightNode;

        // 构造器
        public EmpNode(Employee currVal) {
            this.currVal = currVal;
        }

        /**
         * 前序遍历 <br>
         * 输出顺序: 当前节点值 -> 左节点值 -> 右节点值
         */
        public void preEchoAll(Consumer<EmpNode> consumer) {
            // 使用当前节点值
            consumer.accept(this);
            // 使用左节点值
            if (leftNode != null) leftNode.preEchoAll(consumer);
            // 使用右边的节点值
            if (rightNode != null) rightNode.preEchoAll(consumer);
        }

        /**
         * 中序遍历 <br>
         * 输出顺序: 左节点值 -> 当前节点值 -> 右边节点值
         */
        public void currEchoAll(Consumer<EmpNode> consumer) {
            // 使用左节点值
            if (leftNode != null) leftNode.currEchoAll(consumer);
            // 使用当前节点值
            consumer.accept(this);
            // 使用右边的节点值
            if (rightNode != null) rightNode.currEchoAll(consumer);
        }

        /**
         * 后序遍历 <br>
         * 输出顺序: 左节点值 -> 右节点值 -> 当前节点值
         */
        public void afterEchoAll(Consumer<EmpNode> consumer) {
            // 左边的节点
            if (leftNode != null) leftNode.afterEchoAll(consumer);
            // 右边的节点
            if (rightNode != null) rightNode.afterEchoAll(consumer);
            // 当前的值
            consumer.accept(this);
        }

        // --- setter ---
        // 当前的值不允许设置
        // public void setCurrVal(Integer currVal) {
        //     this.currVal = currVal;
        // }

        public void setLeftNode(EmpNode leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(EmpNode rightNode) {
            this.rightNode = rightNode;
        }

        // --- getter ---
        public Employee getCurrVal() {
            return currVal;
        }

        public EmpNode getLeftNode() {
            return leftNode;
        }

        public EmpNode getRightNode() {
            return rightNode;
        }

        @Override
        public String toString() {
            String curr = String.format("currVal=(%s)", currVal.getId());
            String left = String.format("leftNodeVal=(%s)", "null");
            if (leftNode != null) left = String.format("leftNodeVal=(%s)", leftNode.getCurrVal().getId());
            String right = String.format("rightNodeVal=(%s)", "null");
            if (rightNode != null) right = String.format("rightNodeVal=(%s)", rightNode.getCurrVal().getId());
            String all = "[" + curr + "," + left + "," + right + "]";
            return all;
        }

    }

}
