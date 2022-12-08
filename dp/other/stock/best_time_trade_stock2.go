/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 17:35:52
 * @LastEditTime: 2022-12-08 17:43:44
 */
package stock

/**
题目描述
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
*/
// 根据题目描述可以看出，与买卖股票1不同的是能够进行多次交易，也就是稍微改动一下状态转移方程即可
func maxProfit2(prices []int) int {
	n := len(prices)
	// dp[i][0]是持有股票的最大收益，dp[i][1]是不持有股票的最大收益
	dp := make([][]int, n)
	for i, _ := range dp {
		dp[i] = make([]int, 2)
	}

	dp[0][0] = -prices[0]
	dp[0][1] = 0
	for i := 1; i < n; i++ {
		dp[i][0] = max(dp[i-1][0], dp[i-1][1]-prices[i])
		dp[i][1] = max(dp[i-1][1], dp[i-1][0]+prices[i])
	}
	return dp[n-1][1]
}
