/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-10 15:57:36
 * @LastEditTime: 2023-01-02 10:56:44
 */
package palindromicsubsequence

/**
题目描述
给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
*/
func longestPalindromeSubseq(s string) int {
	n := len(s)
	// dp[i][j]表示的是[i,j]范围内的回文子序列的最长长度
	dp := make([][]int, n)
	for i, _ := range dp {
		dp[i] = make([]int, n)
		dp[i][i] = 1
	}

	// for i := 0; i < n; i++ {
	// 	for j := 0; j < i; j++ {
	// 		if s[j] == s[i] {
	// 			dp[i][j] = 2 + dp[i-1][j+1]
	// 		} else {
	// 			// dp[i-1][j+1]肯定是比下面这两个小的，想范围
	// 			dp[i][j] = max(dp[i][j+1], dp[i-1][j])
	// 		}
	// 	}
	// }
	// 像这个二维表格，需要求右上角的值，
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				dp[i][j] = 2 + dp[i+1][j-1]
			} else {
				dp[i][j] = max(dp[i+1][j], dp[i][j-1])
			}
		}
	}
	// 已知对角线，求右上角的值，labuladong！！！一定要注意遍历顺序
	return dp[0][n-1]
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}

/**
给你一个字符串 s，找到 s 中最长的回文子串。
上面是返回最长回文子串的长度，这里是返回最长回文子串
*/
func longestPalindromeSubseq2(s string) string {
	n := len(s)
	// dp[i][j]表示的是[i,j]范围内的子序列是否为回文序列
	dp := make([][]bool, n)
	for i, _ := range dp {
		dp[i] = make([]bool, n)
		dp[i][i] = true
	}
	maxLen, left, right := 0, 0, 0

	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] != s[j] {
				dp[i][j] = false
			} else {
				// s[i] == s[j]
				if j-i == 1 {
					dp[i][j] = true
				} else {
					dp[i][j] = dp[i+1][j-1]
				}
			}

			if dp[i][j] && j-i+1 > maxLen {
				maxLen = j - i + 1
				left = i
				right = j
			}
		}
	}
	return s[left : right+1]
}
