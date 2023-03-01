package parentheses

func longestValidParentheses(s string) int {
	n := len(s)
	if n == 0 {
		return 0
	}
	dp := make([]int, n) // dp[i]表示的是以第i个字符结尾的最长有效括号的长度
	dp[0] = 0
	ans := 0

	for i := 1; i < n; i++ {
		if s[i] == ')' {
			pre := i - dp[i-1] - 1
			if pre >= 0 && s[pre] == '(' {
				dp[i] = dp[i-1] + 2
				if pre > 0 {
					// ")()())" 根据这个case得到的这段逻辑
					dp[i] += dp[pre-1]
				}
			} else {
				dp[i] = 0
			}
		}

		ans = max(ans, dp[i])
	}

	return ans
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
