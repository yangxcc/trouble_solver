package splitinteger

/**
给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。

返回 你可以获得的最大乘积 。
*/
func integerBreak(n int) int {
	memory := make([]int, n+1)
	memory[2] = 1

	var integerBreakHelper func(x int) int
	integerBreakHelper = func(x int) int {
		if memory[x] != 0 {
			return memory[x]
		}

		tmp := 0
		// 从2开始对n进行拆分
		for i := 2; i < x; i++ {
			tmp = max(tmp, max(i*(x-i), integerBreakHelper(x-i)))
		}
		memory[x] = tmp
		return memory[x]
	}

	return memory[n]
}

func integerBreak2(n int) int {
	// dp[i]表示的是i拆分成k个正整数的最大乘积
	dp := make([]int, n+1)
	dp[2] = 1

	// 从3开始对n进行拆分
	for i := 3; i <= n; i++ {
		// 对i进行拆分
		for j := 1; j < i; j++ {
			dp[i] = max(dp[i], max(j*(i-j), dp[i-j]*j))
		}
	}

	return dp[n]
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
