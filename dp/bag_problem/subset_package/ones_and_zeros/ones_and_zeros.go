/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-06 20:43:33
 * @LastEditTime: 2022-12-06 22:03:11
 */
package onesandzeros

/**
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
*/
// 本质上也是一个0-1背包，比较特殊的是这道题目中的背包有两个树形，m和n
func findMaxForm(strs []string, m int, n int) int {
	// 按照惯例，这个我们应该设置成三维dp，dp[k][i][j]表示前k个数能够组合成最多有m个0，n个1的子集的最大长度
	// 因为dp[k][i][j]只和k-1和k有关，所以可以进行状态压缩
	// dp[i][j]表示的是最多有i个0，j和1的最长子集
	dp := make([][]int, m+1)
	for i, _ := range dp {
		dp[i] = make([]int, n+1)
	}
	dp[0][0] = 0

	for _, str := range strs {
		zerosNums, oneNums := countZerosAndOnes(str)
		// 这里需要注意的是要倒着遍历（状态压缩之后，就先遍历物品，后倒叙遍历背包）
		for i := m; i >= zerosNums; i-- {
			for j := n; j >= oneNums; j-- {
				// if i-zerosNums < 0 || j-oneNums < 0 {
				// 	continue
				// }
				dp[i][j] = max(dp[i][j], dp[i-zerosNums][j-oneNums]+1)
			}
		}
	}
	return dp[m][n]
}

func countZerosAndOnes(str string) (int, int) {
	zeroNums, oneNums := 0, 0
	for i, _ := range str {
		if str[i] == '1' {
			oneNums++
		} else {
			zeroNums++
		}
	}
	return zeroNums, oneNums
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
