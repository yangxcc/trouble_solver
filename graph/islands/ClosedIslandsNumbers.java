package graph.islands;

/**
 * leetcode 1254 middle 统计封闭岛屿的数目
 * 
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 * 请返回 封闭岛屿 的数目。
 */
public class ClosedIslandsNumbers {
    // 其实这道题目和leetcode 1020 middle 飞地的数量 是一样的
    // 一点不同就是返回值不同，此题是连成片的岛屿算是一个
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;

        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    // 唯一的一点不同就在这里，将连成片的岛屿算成一个
                    dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
