/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 10:40:29
 * @LastEditTime: 2022-12-09 10:55:07
 */
package robber

/**
题目描述
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
*/
func rob1(nums []int) int {
	n := len(nums)
	// dp[i][0]表示的是偷第i户的最大收益，dp[i][1]表示的是不偷第i户的最大收益
	dp := make([][]int, n)
	for i, _ := range dp {
		dp[i] = make([]int, 2)
	}
	dp[0][0] = nums[0]
	dp[0][1] = 0

	for i := 1; i < n; i++ {
		dp[i][0] = max(dp[i-1][1]+nums[i], dp[i-1][0])
		dp[i][1] = max(dp[i-1][0], dp[i-1][1])
	}
	return max(dp[n-1][0], dp[n-1][1])
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
