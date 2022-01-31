package com.yutianhui.learning.algorithm.tree.huffmancoding;

import java.util.Arrays;

/**
 * 测试 HuffmanTree
 *
 * @author yutianhui
 * @date 2022/1/5 14:23
 */
public class ByteHuffmanTreeTest {

    public static void main(String[] args) {
        // huffmantree 测试工作
        String string = "Every day of your life, it is important to take the time to “smell the roses” — to appreciate the experiences that lead to happiness. This is part of being truly happy.";
        //String string = "eeeeee";
        byte[] bytes = string.getBytes();
        // 输出数组
        System.out.println("原始数组: " + Arrays.toString(bytes));
        System.out.println("原始数组长度: " + bytes.length);
        System.out.println("统计: " + ByteHuffmanTree.staticies(bytes));

        // 构建树
        ByteHuffmanTree byteHuffmanTree = new ByteHuffmanTree(ByteHuffmanTree.staticies(bytes));
        System.out.println("获取到HuffmanTable: " + byteHuffmanTree.getHuffmanCodingTable());
        System.out.println("编码后的字符串: " + byteHuffmanTree.encodeStr(bytes, byteHuffmanTree.getHuffmanCodingTable()));

        // 编码后的数组
        byte[] encodes = byteHuffmanTree.encodeToBytes(bytes);
        System.out.println("编码后的字节数组: " + Arrays.toString(encodes));
        System.out.println("压缩后: " + encodes.length + String.format(" | 压缩率: %.2f%%", (bytes.length - encodes.length + 0.0) / bytes.length * 100));

        // 解码
        byte[] decodes = byteHuffmanTree.decodeToBytes(encodes);
        System.out.println("解码后: " + Arrays.toString(decodes));
        System.out.println("结果: " + new String(decodes));

    }

}
