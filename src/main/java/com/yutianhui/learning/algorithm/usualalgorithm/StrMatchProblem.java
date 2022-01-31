package com.yutianhui.learning.algorithm.usualalgorithm;

/**
 * 暴力匹配算法测试
 *
 * @author yutianhui
 * @date 2022/1/10 13:20
 */
public class StrMatchProblem {

    public static void main(String[] args) {
        // 暴力匹配算法进行测试
        String origin = "oooAAAabbiikkK";
        // 要匹配的字符串
        String match = "ii";
        // 调用匹配
        int strMatch = strMatch(origin, match);
        // 输出语句
        System.out.println("匹配到首次出现的位置(index): " + strMatch);
        if (strMatch != -1) System.out.println("检查: " + origin.substring(strMatch, strMatch + match.length()));
    }


    /**
     * 暴力匹配算法 <br>
     * 逐个位置的进行匹配 <br>
     *
     * @param origin 原始的字符串
     * @param match  要匹配的字符串
     * @return match在origin中首次出现的位置
     */
    public static int strMatch(String origin, String match) {
        if (origin.length() < 1 || match.length() < 1) return -1; // 不满足匹配的条件
        int originIndex = 0; // 原始字符串的索引
        int matchIndex = 0; // 要匹配的字符串的索引
        int temp = 0; // 临时索引,记录每次从哪里开始查找
        // 循环匹配
        while (originIndex < origin.length()) {
            // 遍历origin
            while (temp <= origin.length()) {
                // 找到结果返回
                if (matchIndex == match.length()) {
                    return temp - matchIndex;
                }
                // 找到最后,没找到
                if (temp == origin.length()) break;
                // 没有匹配上退出
                if (origin.charAt(temp) != match.charAt(matchIndex)) {
                    matchIndex = 0;
                    break;
                }
                temp++;
                matchIndex++;
            }
            originIndex++;
            temp = originIndex;
        }
        // 返回结果
        return -1;
    }


}
