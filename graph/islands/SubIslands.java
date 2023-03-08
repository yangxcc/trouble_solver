package graph.islands;

/**
 * leetcode 1905 middle 统计子岛屿
 * 
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。
 * 一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，
 * 也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
 * 
 * 请你返回 grid2 中 子岛屿 的 数目 。
 */
public class SubIslands {
    /**
     * 这道题目如果正着想，需要将grid1和grid2中的岛屿都序列化了，但是这个序列化需要记录下内容和位置，非常不好弄
     * 所以，我们需要反着弄，子岛屿的定义是grid2中该岛屿的每一个格子都被grid1中同一个岛屿完全包含，那么不是子岛屿的
     * 部分是 grid2中该岛屿的任意一个格子都不被grid1中的同一个岛屿包含，对于这样的岛屿我们直接淹掉，最后再统计grid2
     * 中的岛屿
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 把grid2中肯定不是子岛屿的淹掉
                if (grid2[i][j] == 1 && grid1[i][j] == 0) {
                    dfs(grid2, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    res++;
                    dfs(grid2, i, j);
                }
            }
        }

        return res;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }

        grid[i][j] = 0;

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
