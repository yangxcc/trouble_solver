/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 19:58:46
 * @LastEditTime: 2022-12-13 13:59:37
 */
package rangesum2d

/**
题目描述
给定一个二维矩阵 matrix，以下类型的多个请求：
计算其子矩形范围内元素的总和，该子矩阵的 左上角为 (row1, col1) ，右下角为 (row2, col2) 。
实现 NumMatrix 类：

NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
*/
type NumMatrix struct {
	prefixSum [][]int
}

func Constructor(matrix [][]int) NumMatrix {
	n, m := len(matrix), len(matrix[0])
	// preSum[i][j]表示的是[0,0]到[i-1,j-1]的范围和，相当于左上包了一层
	preSum := make([][]int, n+1)
	for i, _ := range preSum {
		preSum[i] = make([]int, m+1)
	}

	preSum[0][0] = matrix[0][0]
	for i := 1; i <= n; i++ {
		for j := 1; j <= m; j++ {
			preSum[i][j] = matrix[i-1][j-1] + preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1]
		}
	}
	return NumMatrix{prefixSum: preSum}
}

// 最后的范围求值，一定要对照着图片中的数组来计算
func (this *NumMatrix) SumRegion(row1 int, col1 int, row2 int, col2 int) int {
	return this.prefixSum[row2+1][col2+1] + this.prefixSum[row1][col1] -
		this.prefixSum[row2+1][col1] - this.prefixSum[row1][col2+1]
}
