/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-20 16:00:51
 * @LastEditTime: 2023-03-09 14:30:16
 */
package path;

/**
 * leetcode 63 middle 不同路径2
 * 在 leetcode64（path1）的基础上在网格中加入了障碍物
 */
public class Path2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        // 如果终点上有障碍物
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        // dp[i][j]表示的是从起点到[i, j]的路径数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            // 第一列
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            // 第一行
            if (obstacleGrid[0][j] == 1) {
                break;
            } else {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j - 1] == 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                if (obstacleGrid[i - 1][j] == 0) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }   
}
