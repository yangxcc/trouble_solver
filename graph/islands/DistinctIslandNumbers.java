package graph.islands;

import java.util.HashSet;

/**
 * leetcode 694 middle 不同岛屿的数量
 * 
 * 给定一个非空01二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。
 * 请你计算这个网格中共有多少个形状不同的岛屿。
 * 两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
 */
public class DistinctIslandNumbers {
    /**
     * 前面那道题 leetcode 1905 统计子岛屿 有trick，能够反着想，但是这道题，只需要序列化出岛屿的形状，但是不用记录顺序
     * 
     * 针对相同形状的岛屿，如果从同一节点出发，dfs遍历顺序是固定的，结果肯定是一样的
     * 
     * @param grid
     * @return
     */
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        HashSet<String> record = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 0); // 初始方向随意
                    record.add(sb.toString());
                }
            }
        }

        return record.size();
    }

    public void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }

        /**
         * dir
         * 1, 2, 3, 4 分别代表 上下左右
         * -1, -2, -3, -4 分别代表 撤销上，撤销下，撤销左，撤销右
         */
        grid[i][j] = 0;

        sb.append(dir).append(',');

        dfs(grid, i - 1, j, sb, 1);
        dfs(grid, i + 1, j, sb, 2);
        dfs(grid, i, j - 1, sb, 3);
        dfs(grid, i, j + 1, sb, 4);

        // 必须要记录撤销，因为「下，右，撤销右，撤销下」和「下，撤销下，右，撤销右」显然是两个不同的遍历顺序，
        // 但如果不记录撤销操作，那么它俩都是「下，右」，成了相同的遍历顺序，显然是不对的。
        sb.append(-dir).append(',');
    }
}
