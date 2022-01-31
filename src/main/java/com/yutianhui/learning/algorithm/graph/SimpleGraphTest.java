package com.yutianhui.learning.algorithm.graph;

/**
 * 图的测试类
 *
 * @author yutianhui
 * @date 2022/1/6 20:58
 */
public class SimpleGraphTest {

    public static void main(String[] args) {
        // 使用二维数组表示图
        // 使用String类型的顶点
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        // 创建图
        SimpleGraph<String> simpleGraph = new SimpleGraph<>(vertexs);
        // 创建链接(边)
        simpleGraph
                .makeWay(0, 1).makeWay(0, 2)
                .makeWay(1, 3).makeWay(1, 4)
                .makeWay(2, 5).makeWay(2, 6)
                .makeWay(3, 7).makeWay(4, 7)
        ;
        // 输出图
        System.out.println(simpleGraph);

        // DFS遍历
        StringBuilder strb = new StringBuilder();
        simpleGraph.depthFirstSearch(s -> strb.append(s + " --> "), 0);
        strb.delete(strb.length() - 5, strb.length());
        System.out.println("DFS: " + strb);

        // BFS遍历
        StringBuilder strb1 = new StringBuilder();
        simpleGraph.broadFirstSearch(s -> strb1.append(s).append(" --> "), 0);
//        simpleGraph.broadFirstSearch(null, 0);
        strb1.delete(strb1.length() - 5, strb1.length());
        System.out.println("BFS: " + strb1);

    }

}
