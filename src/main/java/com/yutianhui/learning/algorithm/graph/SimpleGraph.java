package com.yutianhui.learning.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * 简单的图实现类,使用的是无方向的链接
 *
 * @author yutianhui
 * @date 2022/1/6 20:37
 */
public class SimpleGraph<T> {
    // 存储的图中的节点
    private ArrayList<Vertex<T>> nodes = new ArrayList<>();
    // 存储的节点之间的链接关系,图的二维数组表示
    private int[][] ways;

    // 构造器
    public SimpleGraph(T[] ts) {
        // 初始化节点集合
        for (int i = 0; i < ts.length; i++) {
            nodes.add(new Vertex<>(ts[i]));
        }
        // 初始化链接关系的二维数组
        ways = new int[ts.length][ts.length];
    }

    /**
     * 创建两个顶点之间的边 <br>
     * 1代表链接 <br>
     * 0代表没有链接 <br>
     *
     * @param a 索引a
     * @param b 索引b
     */
    public SimpleGraph<T> makeWay(int a, int b) {
        // 不符合条件退出
        if (a < 0 || a > ways.length - 1 || b < 0 || b > ways.length || a == b) {
            System.out.println("makeWay失败: a=" + a + ",b=" + b);
            return this;
        }
        // a 链接 b 就相当于 b 链接 a
        // a 链接 b
        ways[b][a] = 1;
        // b 链接 a
        ways[a][b] = 1;
        return this;
    }

    /**
     * 获取两个vertex之间的链接 <br>
     *
     * @param aIndex 索引a
     * @param bIndex 索引b
     */
    public int getWay(int aIndex, int bIndex) {
        // 不满足条件
        if (aIndex == bIndex ||
                aIndex < 0 || aIndex > ways.length - 1 ||
                bIndex < 0 || bIndex > ways.length - 1
        ) return 0;
        return ways[aIndex][bIndex];
    }


    /**
     * 向外部暴露的DFS遍历调用方法 <br>
     *
     * @param consumer   定义的消费者
     * @param startIndex 当前遍历的索引,从那个vertex开始
     */
    public void depthFirstSearch(Consumer<T> consumer, int startIndex) {
        Consumer<Vertex<T>> vertexConsumer = vertex -> {
            if (consumer != null) consumer.accept(vertex.getValue());
            else System.out.println(vertex);
        };
        dfs(vertexConsumer, startIndex, startIndex);
        // 重置访问属性,重置为 false
        nodes.forEach(Vertex::reset);
    }

    /**
     * 深度优先遍历 DFS <br>
     * 递归的进行 <br>
     *
     * @param consumer    定义的消费者
     * @param currIndex   当前遍历的索引,从那个vertex开始
     * @param parentIndex 父顶点的索引
     */
    private void dfs(Consumer<Vertex<T>> consumer, int currIndex, int parentIndex) {
        // 停止条件
        if (currIndex < 0 || currIndex > ways.length - 1 || nodes.get(currIndex).isAccess) return;

        // 当前顶点
        Vertex<T> tVertex = nodes.get(currIndex);
        tVertex.setAccess(true); // 设置已经访问
        consumer.accept(tVertex);

        // 找出临近元素的索引
        int nextIndex = currIndex + 1;
        while (nextIndex != currIndex) {
            // 超过设置为0,重新比较
            if (nextIndex > ways.length - 1) {
                nextIndex = 0;
                continue;
            }
            // 尝试找到链接的节点,找到退出循环
            if (getWay(currIndex, nextIndex) == 1 && !nodes.get(nextIndex).isAccess)
                dfs(consumer, nextIndex, parentIndex);
            // 条件增加
            nextIndex++;
        }

        // 没有找到链接的顶点,遍历下一个索引
        if (parentIndex == currIndex) {
            nextIndex++;
            while (nextIndex != currIndex) {
                // 超过设置为0
                if (nextIndex > ways.length - 1) {
                    nextIndex = 0;
                    continue;
                }
                dfs(consumer, nextIndex, parentIndex);
                nextIndex++;
            }
        }
    }

    /**
     * 广度优先遍历实现 <br>
     * 优先输出当前顶点和所能链通的全部节点 <br>
     *
     * @param tConsumer  使用的处理实现
     * @param startIndex 起始的索引值
     */
    public void broadFirstSearch(Consumer<T> tConsumer, int startIndex) {
        // 转换消费者
        Consumer<Vertex<T>> vertexConsumer = vertex -> {
            if (tConsumer != null) tConsumer.accept(vertex.getValue());
            else System.out.println(vertex);
        };
        // 需要创建一个队列存储访问的临近的元素
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startIndex);
        // 调用递归BFS
        bfs(vertexConsumer, startIndex, queue);
        // 重置元素
        nodes.forEach(Vertex::reset);
    }

    /**
     * 广度优先遍历 BFS <br>
     * 递归进行操作的 <br>
     *
     * @param vertexConsumer 处理实现
     * @param parentIndex    最上层的索引
     * @param queue          存储信息的队列
     */
    private void bfs(Consumer<Vertex<T>> vertexConsumer, int parentIndex, LinkedList<Integer> queue) {
        // 停止条件
        Integer head = queue.getFirst(); // 弹出一个索引
        queue.removeFirst(); // 删除元素

        // 处理元素
        Vertex<T> tVertex = nodes.get(head);
        tVertex.setAccess(true);
        vertexConsumer.accept(tVertex);

        // 使用
        int nextIndex = head + 1;
        while (nextIndex != head) {
            // 超过设置为0
            if (nextIndex > ways.length - 1) {
                nextIndex = 0;
                continue;
            }
            // 条件: 有边,没有被访问过,没有被添加到队列中
            if (getWay(head, nextIndex) == 1 && !nodes.get(nextIndex).isAccess && !queue.contains(nextIndex)) {
                // 添加到队列
                queue.add(nextIndex);
            }
            // 条件增加
            nextIndex++;
        }

        // 判断是否是空的队列
        if (!queue.isEmpty()) {
            bfs(vertexConsumer, parentIndex, queue);
        }
        // 查找没有访问的
        else if (head == parentIndex) {
            // 没有找到相连接的顶点
            // 找到下一个没有被访问的继续递归
            nextIndex++;
            while (nextIndex != head) {
                // 超过设置为0
                if (nextIndex > ways.length - 1) {
                    nextIndex = 0;
                    continue;
                }
                if (!nodes.get(nextIndex).isAccess) {
                    // 没有配访问过
                    queue.add(nextIndex);
                    bfs(vertexConsumer, parentIndex, queue);
                }
                // 条件增加
                nextIndex++;
            }

        }
    }


    /**
     * 顶点实现类 <br>
     * 泛型需要指定的 <br>
     */
    private static class Vertex<T> {
        // 当前顶点的值
        private T value;
        // 是否被访问过,默认没有访问过,每次遍历需要重置
        private boolean isAccess = false;

        // 构造器
        public Vertex(T value) {
            this.value = value;
        }

        // -- 重置节点 --
        public void reset() {
            this.isAccess = false;
        }

        // -- getter -- setter --
        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public boolean isAccess() {
            return isAccess;
        }

        public void setAccess(boolean access) {
            isAccess = access;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    ", isAccess=" + isAccess +
                    '}';
        }
    }

    @Override
    public String toString() {
        // 输出代表图的数组
        StringBuilder strb = new StringBuilder();
        for (int[] way : ways) {
            strb.append(Arrays.toString(way)).append("\n");
        }
        return strb.toString();
    }
}
