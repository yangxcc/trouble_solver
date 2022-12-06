package coinchange

import (
	"math"
)

/**
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。
*/
// 可以看到硬币数量是无限的，所以是完全背包问题
func coinChange(coins []int, amount int) int {
	// dp[i]表示的是凑成i需要的最少硬币个数
	dp := make([]int, amount+1)
	// 初始化
	for i := 0; i <= amount; i++ {
		dp[i] = math.MaxInt - 1 // 其实直接初始化成amount+1即可，因为最多是由amount个面值为1的金币构成
	}
	dp[0] = 0
	for i := 0; i < len(coins); i++ {
		for j := 1; j <= amount; j++ {
			if j-coins[i] < 0 {
				// 只能是不选
				continue
			}
			// 是否选择第i个硬币
			dp[j] = min(dp[j-coins[i]]+1, dp[j])
		}
	}
	if dp[amount] == math.MaxInt-1 {
		return -1
	}
	return dp[amount]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
