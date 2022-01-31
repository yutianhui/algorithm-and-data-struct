package com.yutianhui.learning.algorithm.tree.huffmancoding;

import java.util.*;
import java.util.function.Consumer;

/**
 * 将字节存放到huffman树中进行使用
 *
 * @author yutianhui
 * @date 2022/1/4 17:16
 */
public class ByteHuffmanTree {
    // 存储存入的节点信息
    private ArrayList<Node> nodes = new ArrayList<>();
    // 记录节点的个数
    private Integer count = 0;
    // 根节点
    private Node root;

    // 空参构造器
    // 将传递进去的数组构建为一个 HuffmanTree
    public ByteHuffmanTree(Map<Byte, Integer> charMap) {
        // 转换为node添加到集合中
        charMap.forEach((key, value) -> {
            nodes.add(new Node(value, key));
            count++;
        });
        // 创建Huffman树
        createHuffmanTree();
    }

    /**
     * 创建HuffmanTree实现 <br>
     * 思路: <br>
     * 1. 取出前两个node组成树后放回进行排序后取出前两个继续组成树 <br>
     * 2. 直到剩下一个为止 <br>
     */
    private void createHuffmanTree() {
        // 只有一个节点
        if (nodes.size() == 1) {
            Node node = nodes.get(0);
            root = new Node(node.currVal * 2, (byte) -1, null, node);
            return;
        }
        // 循环,字节数量大于1
        while (true) {
            // 构建完成
            if (nodes.size() <= 1) {
                root = nodes.get(0);
                break;
            }
            // 起码有两个node
            nodes.sort(Comparator.comparingInt(pre -> pre.currVal)); // 排序
            // 取出前面两个
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // 组成树
            Node mid = new Node(left.currVal + right.currVal, (byte) -1, left, right);
            // 删除取出的两个
            nodes.remove(0);
            nodes.remove(0);
            // 将mid添加进去
            nodes.add(mid);
        }

    }

    /**
     * 获取Huffman编码表
     * 规定: 到左节点为 0,右节点为 1
     *
     * @return 疯转结果的Map集合
     */
    public Map<Byte, String> getHuffmanCodingTable() {
        // 结果
        Map<Byte, String> result = new HashMap<>();
        StringBuilder code = new StringBuilder();
        getHuffmanCodingTable(result, code, this.root);
        return result;
    }

    /**
     * 获取Huffman编码表的递归方法
     */
    private void getHuffmanCodingTable(Map<Byte, String> result, StringBuilder code, Node node) {
        // 当前节点是空的
        if (node == null) {
            return;
        }
        // 遍历左边
        if (node.leftChild != null) {
            // 改变code
            code.append("0");
            // 是要记录的
            if (node.leftChild.flag) result.put(node.leftChild.aByte, code.toString());
            // 遍历
            getHuffmanCodingTable(result, code, node.leftChild);
            // 删除最后一个索引
            code.deleteCharAt(code.length() - 1);
        }
        // 遍历右边
        if (node.rightChild != null) {
            // 改变code
            code.append("1");
            // 是要记录的
            if (node.rightChild.flag) result.put(node.rightChild.aByte, code.toString());
            // 遍历
            getHuffmanCodingTable(result, code, node.rightChild);
            // 删除最后一个索引
            code.deleteCharAt(code.length() - 1);
        }

    }


    /**
     * 将编码后的字节数组解码为原来的字节数组
     *
     * @param encode 编码的字节数组
     * @return 解码的字节数组
     */
    public byte[] decodeToBytes(byte[] encode) {
        // 获取到编码表
        Map<Byte, String> codingTable = getHuffmanCodingTable();
        Set<Map.Entry<Byte, String>> entrySet = codingTable.entrySet();
        // 将编码字节数组转换为二进制的字符串形式
        String binaryStr = encodeBytesToBinaryStr(encode);
        // 创建容器
        List<Byte> bytes = new ArrayList<>();
        // 将二进制字符串进行解码
        int start = 0, end = start;
        String temp;
        while (start < binaryStr.length()) {
            // 判断末尾的索引
            end++;
            if (end > binaryStr.length()) end = binaryStr.length();
            // 截取
            temp = binaryStr.substring(start, end);
            // 匹配
            for (Map.Entry<Byte, String> entry : entrySet) {
                if (entry.getValue().equals(temp)) {
                    bytes.add(entry.getKey());
//                    System.out.println(temp + "|" + entry.getKey());
                    start = end;
                    break;
                }
            }

        }
        // 转换结果
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bytes.get(i);
        }
        return result;
    }

    /**
     * 将编码后的字节数组解码为原来的字节数组
     *
     * @param encode 编码的字节数组
     * @return 解码的字节数组
     */
    public static byte[] decodeToBytes(byte[] encode, Map<Byte, String> codingTable) {
        // 获取到编码表
        Set<Map.Entry<Byte, String>> entrySet = codingTable.entrySet();
        // 将编码字节数组转换为二进制的字符串形式
        String binaryStr = encodeBytesToBinaryStr(encode);
        // 创建容器
        List<Byte> bytes = new ArrayList<>();
        // 将二进制字符串进行解码
        int start = 0, end = start;
        String temp;
        while (start < binaryStr.length()) {
            // 判断末尾的索引
            end++;
            if (end > binaryStr.length()) end = binaryStr.length();
            // 截取
            temp = binaryStr.substring(start, end);
            // 匹配
            for (Map.Entry<Byte, String> entry : entrySet) {
                if (entry.getValue().equals(temp)) {
                    bytes.add(entry.getKey());
//                    System.out.println(temp + "|" + entry.getKey());
                    start = end;
                    break;
                }
            }

        }
        // 转换结果
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bytes.get(i);
        }
        return result;
    }

    /**
     * 将原始的字节数组通过编码表转换为编码后的字节数组 <br>
     * 起到压缩数据的作用
     *
     * @param origin 原始的字节数组
     * @return 编码后的字节数组
     */
    public byte[] encodeToBytes(byte[] origin) {
        // 获取原始字节数组编码后的二进制字符串
        String encode = encodeStr(origin, getHuffmanCodingTable());
        // 将二进制字符串转换到字节数组中
        byte[] bytes = new byte[((encode.length() + 7) / 8) + 1];
        // 进行转换
        int start = 0, end = start, index = start;
        String sub;
        while (start < encode.length()) {
            end += 8;
            if (end >= encode.length()) end = encode.length();
            sub = encode.substring(start, end);
            // 为了保证最后一位的确定性,使用特殊的处理办法
            if (end == encode.length()) {
                // 记录需要加几个高位的0
                byte tempI = 0;
                for (int i = 0; i < sub.length(); i++) {
                    if (sub.charAt(i) == '1') {
                        break;
                    }
                    // 添加位数
                    tempI++;
                }
                // 添加到字节数组的最后
                bytes[bytes.length - 1] = tempI;
            }
            // 将截取的二进制字符串转换为byte存储
            // System.out.println("sub:" + sub);
            int a = Integer.parseInt(sub, 2);
            bytes[index++] = (byte) a;
            // 条件增加
            start = end;
        }
        return bytes;
    }


    /**
     * 统计数量
     *
     * @param bytes
     */
    public static Map<Byte, Integer> staticies(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte aByte : bytes) {
            map.merge(aByte, 1, Integer::sum);
        }
        return map;
    }

    /**
     * 将编码后的字节数组转换为二进制的字符串形式
     * Integer.toBinaryString(Byte.toUnsignedInt(aByte));
     */
    private static String encodeBytesToBinaryStr(byte[] encode) {
        // 创建结果的接收者
        StringBuilder strb = new StringBuilder();
        // 循环转换
        String binaryString;
        for (int i = 0; i < encode.length - 1; i++) {
            // 转换为二进制字符串
            binaryString = Integer.toBinaryString(Byte.toUnsignedInt(encode[i]));
            // 是倒数第二个字节
            if (i == encode.length - 2) {
                for (byte j = 0; j < encode[encode.length - 1]; j++) {
                    binaryString = "0" + binaryString;
                }
            }
            // 位数小于8,补全
            if (binaryString.length() < 8 && i != encode.length - 2) {
                while (binaryString.length() < 8) {
                    binaryString = "0" + binaryString;
                }
            }
            strb.append(binaryString);
        }
        return strb.toString();
    }

    /**
     * 从编码表中生成编码后的二进制字符串 <br>
     *
     * @param origin 原始需要编码的字节数组
     * @param table  编码表
     * @return 根据编码生成的二进制字符串
     */
    public String encodeStr(byte[] origin, Map<Byte, String> table) {
        // 遍历使用
        Set<Map.Entry<Byte, String>> entrySet = table.entrySet();
        // 存储结果
        StringBuilder result = new StringBuilder();
        // 遍历原始数组
        for (Byte aByte : origin) {
            // 进行比对
            String code = table.get(aByte);
            if (code != null) {
//                result.append(code).append("(" + aByte + ")").append("|");
                result.append(code);
            }
        }
        return result.toString();
    }

    // --- 遍历 ---
    public void preOrder(Consumer<Integer> consumer) {
        Consumer<Node> nodeConsumer = node -> {
            if (consumer != null) consumer.accept(node.currVal);
            else System.out.println(node);
        };
        this.root.preOrder(nodeConsumer);
    }

    /**
     * 获取树的高度
     *
     * @return 返回树的高度
     */
    public int getHeight() {
        return height(root);
    }

    /**
     * 递归计算树的高度
     *
     * @param node 从节点开始
     * @return 返回的树的高度
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int left = height(node.getLeftChild());
            int right = height(node.getRightChild());
            return left > right ? left + 1 : right + 1;
        }
    }


    /**
     * ByteHuffmanTree Node实现类
     */
    private static class Node {
        // 节点的当前权值
        private final Integer currVal;
        private Byte aByte;
        // 是否是一个节点
        private boolean flag = true;
        // 左右节点
        private Node leftChild;
        private Node rightChild;

        // 构造器,权值一旦设定就不能改变了
        public Node(Integer currVal, Byte aByte) {
            this.aByte = aByte;
            this.currVal = currVal;
        }

        // 全参数构造器
        public Node(Integer currVal, Byte aByte, Node leftChild, Node rightChild) {
            this.currVal = currVal;
            this.aByte = aByte;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.flag = false;
        }

        // --- 遍历方法 ---
        // 前序遍历
        public void preOrder(Consumer<Node> consumer) {
            consumer.accept(this);
            if (leftChild != null) leftChild.preOrder(consumer);
            if (rightChild != null) rightChild.preOrder(consumer);
        }

        // --- setter ---
        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        // --- getter ---
        public Integer getCurrVal() {
            return currVal;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public Byte getaByte() {
            return aByte;
        }

        @Override
        public String toString() {
            return String.format("Node [curr=(%s) , aByte=(%s) , flag=(%s)]", currVal, aByte, flag);
        }
    }


}
