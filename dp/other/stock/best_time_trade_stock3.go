/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 17:46:40
 * @LastEditTime: 2022-12-08 19:46:46
 */
package stock

import "math"

/**
给定一个数组，它的第i个元素是一支给定的股票在第i天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
*/
// 可以看到和股票买卖1/2的不同之处在于，限制了交易次数，最多可以完成两笔交易
func maxProfit3(prices []int) int {
	n, maxTradeNums := len(prices), 2
	// dp[i][j][0]表示的是第i天在持有股票的情况下第j次交易的最大收益
	// dp[i][j][1]表示的是第i天在不持有股票的情况下第j次交易的最大收益
	dp := make([][][]int, n)
	for i, _ := range dp {
		dp[i] = make([][]int, maxTradeNums+1)
		for j, _ := range dp[i] {
			dp[i][j] = make([]int, 2)
		}
	}

	// base case 当前交易次数为0的时候
	for i := 0; i < n; i++ {
		dp[i][0][0] = math.MinInt // 当交易次数为0，但持有股票（发生了交易），明显是不符合情况的
		dp[i][0][1] = 0
	}

	for i := 0; i < n; i++ {
		for j := 1; j <= maxTradeNums; j++ {
			// 一定要注意这里，不能忘掉，在第1天的时候base case
			if i-1 == -1 {
				dp[i][j][0] = -prices[0]
				dp[i][j][1] = 0
				continue
			}
			// 只有当买入的时候，才算是一次交易，卖出和他是配套操作，不能再减1了
			dp[i][j][0] = max(dp[i][j-1][1]-prices[i], dp[i-1][j][0])
			dp[i][j][1] = max(dp[i][j][0]+prices[i], dp[i-1][j][1])
		}
	}

	// 尽可能地发生交易，最后不再持有股票
	return dp[n-1][2][1]
}
