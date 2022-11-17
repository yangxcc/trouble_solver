package stock

// 买卖股票问题都可以使用动态规划解决，这一部分在dp中解决
// 这里先用贪心算法来解决
/**
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。
*/
func maxProfit(prices []int) int {
	dp := make([][]int, len(prices))
	for i, _ := range dp {
		dp[i] = make([]int, 2)
	}

	// dp[i][0] 第i天不持有股票的最大收益
	// dp[i][1] 第i天持有股票的最大收益
	dp[0][0] = 0
	dp[0][1] = -prices[0]
	for i := 1; i < len(prices); i++ {
		dp[i][0] = max(dp[i-1][0], dp[i-1][1]+prices[i])
		dp[i][1] = max(dp[i-1][0]-prices[i], dp[i-1][1])
	}
	return dp[len(prices)-1][0]
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}

/**
假设从第1天买入，第4天卖出，这个区间内的收益是最多的
prices[4]-prices[1] = (prices[4]-prices[3])+(prices[3]-prices[2])+(prices[2]-prices[1])
这样就把整个区间的收益转换成了每天的收益，如果我们能够保证每天的收益尽量为正，那么区间的整体收益肯定是最大的
*/
func maxProfitByGreedy(prices []int) int {
	ans := 0
	for i := 1; i < len(prices); i++ {
		ans += max(prices[i]-prices[i-1], 0)
	}
	return ans
}
