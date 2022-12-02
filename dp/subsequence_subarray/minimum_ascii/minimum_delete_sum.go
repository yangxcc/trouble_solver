package minimumascii

import "fmt"

/**
给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
*/
// 其实这道题目和最长公共子序列是一样的（LCS），但是经典LCS是求最长公共子序列的长度，而这个我们需要知道删除的是哪些字符
func minimumDeleteSum(s1 string, s2 string) int {
	m, n := len(s1), len(s2)
	dp := make([][]int, m+1)
	for i, _ := range dp {
		dp[i] = make([]int, n+1)
		// 顺便初始化
		if i > 0 {
			dp[i][0] = dp[i-1][0] + int(s1[i-1])
			// 效果为啥和dp[i][0] += int(s1[i-1])不一样，因为这根本不是累加啊，dp[i][0] = dp[i][0] + int(s1[i-1]), dp[i][0]=0!! 傻...
			// dp[i][0] += int(s1[i-1])
		}
	}

	for j := 1; j <= n; j++ {
		dp[0][j] = dp[0][j-1] + int(s2[j-1])
		// dp[0][j] += int(s2[j-1])
	}

	fmt.Println(dp)

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s1[i-1] == s2[j-1] {
				dp[i][j] = dp[i-1][j-1]
			} else {
				dp[i][j] += min(dp[i-1][j]+int(s1[i-1]), dp[i][j-1]+int(s2[j-1]))
			}
		}
	}
	return dp[m][n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
