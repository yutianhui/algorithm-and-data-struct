package com.yutianhui.learning.algorithm.recursion;

/**
 * 测试小球找路,通过递归调用进行
 *
 * @author yutianhui
 * @date 2021/12/11 20:39
 */
public class FindWayTest {

    public static void main(String[] args) {

        // 使用递归进行找路测试
        // 地图找路测试问题
        // 生成地图进行测试,使用二维数组进行测试
        // 这个地图是七行八列,int[7][8]
        int column = 8;
        int row = 7;
        int[][] map = makeMap(column, row);
        // 展示Map
        showMap(map);
        // 查找路
        boolean way = findWay(map, 1, 1, column, row);
        // 输出结果信息
        System.out.println("是否成功:" + way);
        // 展示Map
        showMap(map);
    }


    /**
     * 使用递归找到出地图的路线
     * 走过的路线使用 2
     * 查找方式 左 -> 下 -> 右 -> 上
     *
     * @param map    要走的地图
     * @param startX 起始X坐标
     * @param startY 起始Y坐标
     * @param col    列数
     * @param row    行数
     */
    public static boolean findWay(int[][] map, int startX, int startY, int col, int row) {
        // 终止条件
        if (startX == col - 2 && startY == row - 2) {
            map[startY][startX] = 2;
            System.out.println("查找完成...");
            return true;
        }
        // 起点错误
        if (map[startY][startX] == 1) {
            return false;
        }
        // 修改为已经走过的地方
        map[startY][startX] = 2;
        // 向左查找
        if (map[startY][startX - 1] == 0) {
            return findWay(map, startX - 1, startY, col, row);
        }
        // 向下查找
        if (map[startY + 1][startX] == 0) {
            return findWay(map, startX, startY + 1, col, row);
        }
        // 向右查找
        if (map[startY][startX + 1] == 0) {
            return findWay(map, startX + 1, startY, col, row);
        }
        // 向上查找
        if (map[startY - 1][startX] == 0) {
            return findWay(map, startX, startY - 1, col, row);
        }
        return false;
    }

    /**
     * 打印展示map的情况
     */
    public static void showMap(int[][] map) {
        System.out.println("查看地图如下:");
        for (int i = 0; i < map.length; i++) {
            int[] row = map[i];
            StringBuilder strb = new StringBuilder();
            for (int i1 = 0; i1 < row.length; i1++) {
                strb.append(row[i1] + " ");
            }
            System.out.println(strb);
        }
    }

    /**
     * 生成二维数组地图 <br>
     * 0 可以走的方块 <br>
     * 1 墙 <br>
     * 2 代表已经走的方块 <br>
     *
     * @param column 地图列数
     * @param row    地图行数
     */
    public static int[][] makeMap(int column, int row) {
        // 生成对应的二维数组
        int[][] map = new int[row][column];
        // 填充地图
        for (int i = 0; i < map.length; i++) {
            // 这一行
            int[] rowArray = map[i];
            // 第一行或者最后一行 全部设置为墙
            if (i == 0 || i == map.length - 1) {
                for (int i1 = 0; i1 < rowArray.length; i1++) {
                    rowArray[i1] = 1;
                }
            }
            // 其余的将第一个和最后一个设置为墙
            for (int i1 = 0; i1 < rowArray.length; i1++) {
                if (i1 == 0 || i1 == rowArray.length - 1) {
                    rowArray[i1] = 1;
                }
            }

        }

        // 生成对应的障碍
        map[3][4] = 1;
        map[3][5] = 1;
        map[4][4] = 1;
        map[5][4] = 1;

        // 返回map
        return map;
    }

}
