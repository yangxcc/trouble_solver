/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 19:48:23
 * @LastEditTime: 2022-12-08 20:01:35
 */
package stock

/**
题目描述
给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
*/
// 与其他题目不同的是，该题中股票的买卖有冷冻期，而且可以尽可能多的完成交易
func maxProfit4(prices []int) int {
	n := len(prices)
	// dp[i][0]表示第i天持有股票的最大利润，dp[i][1]表示第i天不持有股票的最大利润
	dp := make([][]int, n)
	for i, _ := range dp {
		dp[i] = make([]int, 2)
	}
	dp[0][0] = -prices[0]
	dp[0][1] = 0

	for i := 1; i < n; i++ {
		// 需要判断第i-1天不持有，是否处于冷冻期内，判断的标准是第i-2天是否持有，如持有则处于冷冻期
		// dp[i][0] = max(dp[i-1][0], dp[i-1][1]-prices[i])
		if i-2 == -1 {
			// 第1天就正常写，因为无论怎么弄，都不出在冷冻期
			dp[i][0] = max(dp[i-1][0], dp[i-1][1]-prices[i])
			dp[i][1] = max(dp[i-1][1], dp[i-1][0]+prices[i])
			continue
		}

		// 因为第i-1天可能出于冷冻期，所以只有当第i-2天不持有的时候才能够买入
		dp[i][0] = max(dp[i-1][0], dp[i-2][1]-prices[i])
		dp[i][1] = max(dp[i-1][1], dp[i-1][0]+prices[i])
	}
	return dp[n-1][1]
}
