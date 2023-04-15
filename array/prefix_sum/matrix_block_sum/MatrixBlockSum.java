/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 13:38:49
 * @LastEditTime: 2023-04-15 17:47:58
 */
package array.prefix_sum.matrix_block_sum;

/**
 * leetcode 1314 middle 矩阵区域和
 * 
 * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，
 * 其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和： 
 * i - k <= r <= i + k, 
 * j - k <= c <= j + k 且(r, c) 在矩阵内。
 */
public class MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] preSumArray = initPreSumArray(mat, m, n);
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int row1 = Math.max(i - k, 0);
                int col1 = Math.max(j - k, 0);
                int row2 = Math.min(i + k, m - 1);
                int col2 = Math.min(j + k, n - 1);
                
                ans[i][j] = regionSum(preSumArray, row1, col1, row2, col2);
            }
        }

        return ans;
    }

    // 构建二维数组的前缀和数组
    private int[][] initPreSumArray(int[][] mat, int m, int n) {
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        return preSum;
    }

    private int regionSum(int[][] preSum, int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
    }
}

/**
 * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，
 * 其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和： 
 * i - k <= r <= i + k, 
 * j - k <= c <= j + k 且
 * (r, c) 在矩阵内。
 */
class day0415 {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];

        int[][] preSum = initPreSumArray(mat, m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int row1 = Math.max(0, i - k);
                int col1 = Math.max(0, j - k);
                int row2 = Math.min(m - 1, i + k);
                int col2 = Math.min(n - 1, j + k);

                ans[i][j] = regionSum(preSum, row1, col1, row2, col2);
            }
        }

        return ans;
    }

    // 针对mat创建一个前缀和
    private int[][] initPreSumArray(int[][] mat, int m, int n) {
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = mat[i - 1][j - 1] + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
            }
        }

        return preSum;
    }

    private int regionSum(int[][] preSum, int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
    }
}


