/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 20:05:44
 * @LastEditTime: 2022-12-08 20:15:57
 */
package stock

/**
题目描述
给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
*/
// 本题的不同之处在于每笔交易都需要付手续费
func maxProfit5(prices []int, fee int) int {
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
		dp[i][0] = max(dp[i-1][0], dp[i-1][1]-prices[i])
		dp[i][1] = max(dp[i-1][1], dp[i-1][0]+prices[i]-fee)
	}
	return dp[n-1][1]
}
