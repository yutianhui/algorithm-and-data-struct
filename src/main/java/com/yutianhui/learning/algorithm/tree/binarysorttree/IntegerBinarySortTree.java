package com.yutianhui.learning.algorithm.tree.binarysorttree;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * 二叉排序树实现类 (BinarySort Tree) <br>
 * 查找和增删的效率高 <br>
 *
 * @author yutianhui
 * @date 2022/1/5 19:52
 */
public class IntegerBinarySortTree {
    // 根节点
    private Node root;
    // 存储元素的集合
    private ArrayList<Node> nodes = new ArrayList<>();

    // 空参构造器
    public IntegerBinarySortTree() {

    }

    public IntegerBinarySortTree(int[] ints) {
        // 添加到集合中
        for (int anInt : ints) {
            nodes.add(new Node(anInt));
        }
        // 将nodes中的节点排序
        long start = System.currentTimeMillis();
        nodes.sort((prev, next) -> prev.value - next.value);
        long end = System.currentTimeMillis();
        System.out.println("节点排序花费时间: " + (end - start) + "ms");
        // 构建BinarySortTree
        buildBinarySortTree();
    }

    /**
     * 将nodes中的数据构建为二叉排序树 <br>
     */
    private void buildBinarySortTree() {
        // 没有元素
        if (nodes.size() == 0) return;
        // 先确定root节点
        int parent = nodes.size() / 2; // 中间值索引
        root = nodes.get(parent);
        // 元素大于1时构建左右
        if (nodes.size() > 1) {
            // 构建左边的
            buildBinarySortTree(0, parent - 1, parent);
            // 构建右边的
            buildBinarySortTree(parent + 1, nodes.size() - 1, parent);
        }
    }

    /**
     * 递归构建二叉排序树 <br>
     *
     * @param left   左边起始索引
     * @param right  右边结束索引
     * @param parent 父节点索引
     */
    private void buildBinarySortTree(int left, int right, int parent) {
        // 终止条件
        if (left == parent || right == parent) return;
        // 构建
        int mid = (left + right) / 2;
        // 挂在左边
        if (mid < parent) {
            nodes.get(parent).setLeftChild(nodes.get(mid));
        } else {
            nodes.get(parent).setRightChild(nodes.get(mid));
        }
        // 递归构建
        buildBinarySortTree(left, mid - 1, mid);
        buildBinarySortTree(mid + 1, right, mid);
    }


    /**
     * 增加元素 <br>
     * 1.将对应的元素添加后重置 <br>
     * 2.排序 <br>
     * 3.重新构建 <br>
     *
     * @param item 添加的元素
     */
    public void addInt(Integer item) {
        // 1.将对应的元素添加后重置
        nodes.add(new Node(item));
        nodes.forEach(node -> node.resetChild());
        // 2.排序
        nodes.sort((prev, next) -> prev.value - next.value);
        // 3.重新构建
        buildBinarySortTree();
    }

    /**
     * 删除元素 <br>
     * 1.删除集合中的对应的元素 <br>
     * 2.重置所有node <br>
     * 3.重新构建 <br>
     *
     * @param item 要删除的元素
     */
    public void removeInt(Integer item) {
        // 1.删除集合中的对应的元素
        nodes.removeIf(node -> node.value == item);
        // 2.重置所有node
        nodes.forEach(node -> node.resetChild());
        // 3.重新构建
        buildBinarySortTree();
    }

    /**
     * 查找对应的元素返回对应的Node <br>
     * 由于BST是经过排序的,索引查询效率高 <br>
     * 类似于二分查找 <br>
     *
     * @param anInt 要查找的int
     * @return 返回对应的节点
     */
    public Node getInt(int anInt) {
        Node temp = root;
        // 循环查找
        while (temp != null) {
            if (temp.getValue() > anInt) {
                // 向左边查找
                temp = temp.getLeftChild();
            } else if (temp.getValue() < anInt) {
                // 向右边查找
                temp = temp.getRightChild();
            } else {
                // 找到了
                return temp;
            }
        }
        // 没有找到
        return null;
    }

    /**
     * 查找对应的元素返回对应的Node <br>
     * 由于BST是经过排序的,索引查询效率高 <br>
     * 类似于二分查找 <br>
     *
     * @param anInt 要查找的int
     * @return 返回对应的所有节点
     */
    public ArrayList<Node> getIntMuilt(int anInt) {
        Node temp = root;
        // 循环查找
        while (temp != null) {
            if (temp.getValue() > anInt) {
                // 向左边查找
                temp = temp.getLeftChild();
            } else if (temp.getValue() < anInt) {
                // 向右边查找
                temp = temp.getRightChild();
            } else {
                // 找到了,temp.value == anInt
                // 但是要找到多个进行返回
                // 创建对应的数组
                ArrayList<Node> result = new ArrayList<>();
                result.add(temp);
                // 查找
                getNodeByNode(temp, result);
                return result;
            }
        }
        // 没有找到
        return null;
    }

    /**
     * 递归的以node为根节点,把所有的和Node.value一样的node添加到指定的数组中 <br>
     *
     * @param node      以node为根节点
     * @param nodeArray 结果数组
     */
    private void getNodeByNode(Node node, ArrayList<Node> nodeArray) {
        // 停止条件
        if (node == null) return;
        // 查找左边
        if (node.getLeftChild() != null && node.getLeftChild().getValue() == node.getValue()) {
            nodeArray.add(node.getLeftChild());
            getNodeByNode(node.getLeftChild(), nodeArray);
        }
        // 查找右边
        if (node.getRightChild() != null && node.getRightChild().getValue() == node.getValue()) {
            nodeArray.add(node.getRightChild());
            getNodeByNode(node.getRightChild(), nodeArray);
        }
    }

    /**
     * 中序遍历二叉排序树
     *
     * @param consumer 具体的使用者
     */
    public void infixOrder(Consumer<Integer> consumer) {
        Consumer<Node> nodeConsumer = node -> {
            if (consumer != null) consumer.accept(node.value);
            else System.out.println(node);
        };
        if (consumer == null) System.out.println(String.format("root: %s \n------", root));
        this.root.infixOrder(nodeConsumer);
    }


    /**
     * 节点实现内部类
     */
    public static class Node {
        // 当前的值
        private Integer value;
        // 左右节点
        private Node leftChild;
        private Node rightChild;

        public Node(Integer value) {
            this.value = value;
        }

        public Node(Integer value, Node leftChild, Node rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        /**
         * 中序遍历 <br>
         * BinarySortTree的中序遍历结果是升序 <br>
         *
         * @param consumer 传入的是一个消费者
         */
        public void infixOrder(Consumer<Node> consumer) {
            if (leftChild != null) leftChild.infixOrder(consumer);
            consumer.accept(this);
            if (rightChild != null) rightChild.infixOrder(consumer);
        }

        /**
         * 重置节点的左右节点 <br>
         */
        public void resetChild() {
            this.rightChild = null;
            this.leftChild = null;
        }

        // -- getter -- setter --
        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        /**
         * toString方法
         */
        @Override
        public String toString() {
            return String.format("Node [value=(%s),left=(%s),right=(%s)]",
                    value,
                    leftChild == null ? "null" : leftChild.value,
                    rightChild == null ? "null" : rightChild.value
            );
        }

    }

}
