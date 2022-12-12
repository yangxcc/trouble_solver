/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 19:58:53
 * @LastEditTime: 2022-12-12 20:36:16
 */
package array.prefix_sum.range_sum_2d;

public class NumMatrix {

    int[][] prefixSum;
    public NumMatrix(int[][] martix) {
        int m = martix.length;
        int n = martix[0].length;
        // 相当于在matrix左上方包上了一层，表示的是[0,0]到[i-1][j-1]范围和
        prefixSum = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                prefixSum[i][j] = martix[i-1][j-1] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2+1][col1+1] - prefixSum[row2+1][col1] - prefixSum[row1][col2+1] + prefixSum[row1][col1];
    }
    
}
