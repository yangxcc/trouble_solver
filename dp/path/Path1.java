/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-27 19:14:36
 * @LastEditTime: 2023-03-06 15:15:50
 */
package dp.path;

import java.util.Arrays;
import java.util.Scanner;

/**
 * leetcode 62 middle 不同路径
 * 
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 总共有多少条不同的路径？
 */
public class Path1{
    public int uniquePaths(int m, int n) {
        // dp[i][j]表示的是从起点到[i, j]的路径数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            // 第一列
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            // 第一行
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * leetcode 64 middle 最小路径和
     * 
     * 相同的题目，只不过从路径数变成了路径和
     * 
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * 
     * 这里写dp函数，dp数组同理
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return dp(grid, m - 1, n - 1);
    }

    int[][] memo;
    // 从起点到[i, j]的最短距离
    public int dp(int[][] grid, int i, int j) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }

        memo[i][j] = Math.min(dp(grid, i, j - 1), dp(grid, i - 1, j)) + grid[i][j];
        return memo[i][j];
    }
}

/**
 * leetcode 64 middle 最小路径和
 * 
 * 相同的题目，只不过从路径数变成了路径和
 * 
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 
 * 这里写dp函数，dp数组同理
 */
class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = in.nextInt();
            }
        }

        System.out.println(solve(grid, m, n));
    }

    public static int solve(int[][] grid, int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }
}