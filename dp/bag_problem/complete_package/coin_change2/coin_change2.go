package coinchange2

/**
给定不同⾯额的硬币 coins 和⼀个总⾦额 amount，写⼀个函数来计算可以凑成总⾦额的硬币组合数。
假设每⼀种⾯额的硬币有⽆限个。
*/
func change(amount int, coins []int) int {
	n := len(coins)
	// dp[i][j]表示用前i个金币能够凑成总金额j的组合数
	dp := make([][]int, n+1)
	for i, _ := range dp {
		dp[i] = make([]int, amount+1)
	}
	// base case
	for i := 0; i <= n; i++ {
		dp[i][0] = 1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= amount; j++ {
			if j-coins[i-1] < 0 {
				continue
			}
			dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j]
		}
	}

	return dp[n][amount]
}
