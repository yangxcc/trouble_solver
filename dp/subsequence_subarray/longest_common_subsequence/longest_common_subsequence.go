package longestcommonsubsequence

/**
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
*/
func longestCommonSubsequence(text1 string, text2 string) int {
	m, n := len(text1), len(text2)
	// dp[i][j]表示text1[0...i]和text2[0...j]的最长公共子序列
	dp := make([][]int, m+1)
	for i, _ := range dp {
		dp[i] = make([]int, n+1)
	}
	// 当text1或者text2为空串时，即第一行和第一列为0
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if text1[i-1] == text2[j-1] {
				dp[i][j] = dp[i-1][j-1] + 1
			} else {
				// 不用加上dp[i-1][j-1]，因为最大值肯定是在dp[i-1][j], dp[i][j-1]之间
				// dp[i-1][j-1]表示的是 公共子序列中肯定是不包含test1[i]和test2[j]的
				dp[i][j] = max(dp[i-1][j], dp[i][j-1])
			}
		}
	}
	return dp[m][n]
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}

/**
leetcode 583 两个字符串的删除操作
	给定两个单词word1和word2，返回使得word1和word2相同所需的最小步数。
	每步可以删除任意一个字符串中的一个字符。
*/
// 思路就是求得两个字符串的最长公共子序列
func minDistance(word1 string, word2 string) int {
	lcs := longestCommonSubsequence(word1, word2)
	return len(word1) - lcs + len(word2) - lcs
}
