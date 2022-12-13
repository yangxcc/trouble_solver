/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 13:39:03
 * @LastEditTime: 2022-12-13 13:56:50
 */
package matrix_block_sum

/**
题目描述
给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，
其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：

i - k <= r <= i + k,
j - k <= c <= j + k 且
(r, c) 在矩阵内。
*/

// 之所以写这个题目，是为了记住一个小技巧：通过 min, max 函数优雅避免索引越界的技巧
func matrixBlockSum(mat [][]int, k int) [][]int {
	m, n := len(mat), len(mat[0])
	ans := make([][]int, m)
	for i, _ := range ans {
		ans[i] = make([]int, n)
	}

	// 根据mat构建前缀和
	prefixSum := initPrefixSum(mat)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			// 左上角
			row1 := max(i-k, 0)
			col1 := max(j-k, 0)

			// 右下角
			row2 := min(i+k, m-1)
			col2 := min(j+k, n-1)
			ans[i][j] = sumRegion(prefixSum, row1, col1, row2, col2)
		}
	}
	return ans
}

func initPrefixSum(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	prefixSum := make([][]int, m+1)
	for i, _ := range prefixSum {
		prefixSum[i] = make([]int, n+1)
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + mat[i-1][j-1]
		}
	}

	return prefixSum
}

func sumRegion(prefixSum [][]int, i, j, p, q int) int {
	return prefixSum[p+1][q+1] - prefixSum[p+1][j] - prefixSum[i][q+1] + prefixSum[i][j]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
