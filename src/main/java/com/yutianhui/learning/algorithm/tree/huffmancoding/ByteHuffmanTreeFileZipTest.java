package com.yutianhui.learning.algorithm.tree.huffmancoding;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

/**
 * 使用赫夫曼编码测试压缩文件
 *
 * @author yutianhui
 * @date 2022/1/5 16:40
 */
public class ByteHuffmanTreeFileZipTest {

    public static void main(String[] args) throws Exception {
        // 提供文件读写路径
        String src = "files/huatu.bmp";
        String dest = "files/huatu.huffZip";
        String unzip = "files/huatu-unzip.bmp";
//        fileZip(src, dest);
        fileUZip(dest, unzip);

    }

    /**
     * 解压文件的方法
     */
    public static void fileUZip(String src, String dest) throws Exception {
        // 读取文件
        ObjectInputStream zipFile = new ObjectInputStream(new FileInputStream(src));
        // 读取编码的字节数组
        byte[] encodes = (byte[]) zipFile.readObject();
        Map<Byte, String> codingTable = (Map<Byte, String>) zipFile.readObject();
        zipFile.close();//关闭
        // 解压文件
        byte[] bytes = ByteHuffmanTree.decodeToBytes(encodes, codingTable);
        // 写回文件
        OutputStream outputStream = new FileOutputStream(dest);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close(); // 关闭流
    }

    /**
     * 压缩文件的方法 <br>
     * 使用赫夫曼编码进行文件的压缩 <br>
     *
     * @param src  源文件
     * @param dest 目标地址
     */
    public static void fileZip(String src, String dest) throws Exception {
        // 获取文件
        InputStream fileInputStream = new FileInputStream(src);
        // 创建字节数组
        int available = fileInputStream.available();
        byte[] all = new byte[available];
        // 读取文件
        int read = fileInputStream.read(all);
        fileInputStream.close(); // 关闭
        System.out.println("fileInputStream.read(all) 读取的: " + read);
        System.out.println("原始文件大小: " + all.length);
        // 构建为树
        ByteHuffmanTree huffmanTree = new ByteHuffmanTree(ByteHuffmanTree.staticies(all));
        // 获取编码表
        Map<Byte, String> codingTable = huffmanTree.getHuffmanCodingTable();
        System.out.println("获取的编码表: " + codingTable);
        System.out.println("编码表的个数: " + codingTable.size());

        // 进行压缩
        byte[] encode = huffmanTree.encodeToBytes(all);
        System.out.println("压缩后的字节数组: " + Arrays.toString(encode));
        System.out.println("压缩后的大小: " + encode.length);

        // 将编码表写入文件
        FileOutputStream outputStream = new FileOutputStream(dest);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(encode); //写入编码字节数组
        objectOutputStream.writeObject(codingTable); //写入编码表
        objectOutputStream.flush();
        objectOutputStream.close();
        outputStream.close();
    }


//    /**
//     * 处理字典的方法
//     * 规则:
//     * --- [0]表示有多少对字典
//     * --- [1]每个byte后面有多少位的byte代表一个String
//     * --- [2]第一个byte
//     *
//     * @param codingTable 字典
//     * @return 返回的处理后的字典字节数组
//     */
//    public static byte[] transformCodingTable(Map<Byte, String> codingTable) {
//        // 找出最大的一位二进制字符串
//        int maxLength = 0;
//        // 遍历找出
//        Set<Map.Entry<Byte, String>> entries = codingTable.entrySet();
//        for (Map.Entry<Byte, String> entry : entries) {
//            if (entry.getValue().length() > maxLength) maxLength = entry.getValue().length();
//        }
//        maxLength = (maxLength + 7) / 8;
//        maxLength++; // 最后一位代表需要补充多少的0
//        // 创建字节数组
//        int count = 2 + codingTable.size() * (maxLength + 1);
//        byte[] result = new byte[count];
//        result[0] = (byte) codingTable.size();
//        result[0] = (byte) maxLength;
//        // 索引
//        int index = 2;
//        // 进行处理
//        for (Map.Entry<Byte, String> entry : entries) {
//            // 获取 byte 和 code
//            byte abyte = entry.getKey();
//            String code = entry.getValue();
//            // 赋值
//            result[index] = abyte;
//            // 处理code
//            int start = 0, end = start; //截取索引
//            String temp;
//            while (start < code.length()) {
//                end += 8;
//                if (end >= code.length()) end = code.length();
//                temp = code.substring(start, end);
//                start = end;
//            }
//
//
//        }
//
//    }

}
