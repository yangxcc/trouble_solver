package coinchange

/**
给你一个整数数组coins ，表示不同面额的硬币；以及一个整数amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。
*/
func coinChange(coins []int, amount int) int {
	if amount == 0 {
		return 0
	}

	// dp[i]表示能够凑出i所需要的最少的硬币数量
	dp := make([]int, amount+1)
	// 这里不太相同的地方在于要将dp实例化为最大值，因为后面是要求最小
	for i := 0; i <= amount; i++ {
		dp[i] = amount + 1
	}
	dp[0] = 0
	for i := 1; i <= amount; i++ {
		for j := 0; j < len(coins); j++ {
			if i-coins[j] < 0 {
				continue
			}
			// 对于第j个金币只有两种选择：选他还是不选他
			dp[i] = min(dp[i-coins[j]]+1, dp[i])
		}
	}

	// 凑不出来的情况
	if dp[amount] == amount+1 {
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
