package dp.path;

import java.util.List;
import java.util.ArrayList;

/**
 * leetcode 980 hard 不同路径3
 * 
 * 在二维网格 grid 上，有 4 种类型的方格：
 * 1 表示起始方格。且只有一个起始方格。
 * 2 表示结束方格，且只有一个结束方格。
 * 0 表示我们可以走过的空方格。
 * -1 表示我们无法跨越的障碍。
 * 
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
 * 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
 */
public class Path3 {
    int ans = 0;
    int countof0 = 0;
    int startX = -1, startY = -1;

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    countof0++;
                } else if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }

        if (startX == -1 || startY == -1) {
            return 0;
        }
        dfs(grid, startX, startY, visited, 0);

        return ans;
    }

    public void dfs(int[][] grid, int i, int j, boolean[][] visited, int step) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == -1 || visited[i][j]) {
            return;
        }

        // 这里一定是0的个数+1，因为我们会把起点（1）算上
        if (grid[i][j] == 2 && step == countof0 + 1) {
            ans++;
            return;
        }

        visited[i][j] = true;

        dfs(grid, i - 1, j, visited, step + 1);
        dfs(grid, i + 1, j, visited, step + 1);
        dfs(grid, i, j - 1, visited, step + 1);
        dfs(grid, i, j + 1, visited, step + 1);

        // 这里是回溯，不是深搜了
        visited[i][j] = false;
    }
}
