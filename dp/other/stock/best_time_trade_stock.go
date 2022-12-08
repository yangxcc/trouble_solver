/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 17:10:56
 * @LastEditTime: 2022-12-08 17:35:22
 */
package stock

/**
题目描述
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
你只能选择 某一天 买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
*/
func maxProfit(prices []int) int {
	n := len(prices)
	// dp[i][0]表示的是第i天持有股票的最大收益,dp[i][1]表示的是第i天不持有股票的最大收益
	dp := make([][]int, n)
	for i, _ := range dp {
		dp[i] = make([]int, 2)
	}

	// base case
	dp[0][0] = -prices[0]
	dp[0][1] = 0
	for i := 1; i < n; i++ {
		// 每天的操作有三种，买入、卖出、不操作
		dp[i][0] = max(-prices[i], dp[i-1][0])           // 持有状态下：买入股票(需要注意题目中的前提，只能买卖一次)/前一天已经持有
		dp[i][1] = max(dp[i-1][0]+prices[i], dp[i-1][0]) // 不持有状态下，卖出股票/前一天就不持有
	}
	return dp[n-1][1]
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
