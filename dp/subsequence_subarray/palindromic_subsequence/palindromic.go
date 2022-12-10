/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-10 12:31:07
 * @LastEditTime: 2022-12-10 15:56:00
 */
package palindromicsubsequence

/**
问题描述
给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
回文字符串 是正着读和倒过来读一样的字符串。
子字符串 是字符串中的由连续字符组成的一个序列。
*/
func countSubstrings(s string) int {
	n := len(s)
	// dp[i]表示的是s[0..i]的回文子串数
	dp := make([]int, n)
	for i := 0; i < n; i++ {
		dp[i] = 1
	}
	// 其实就是暴力算法，时间复杂度都O(n^3)了
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if isPalindromicString(s[j : i+1]) {
				dp[i]++
			}
		}
	}

	var ans int
	for _, v := range dp {
		ans += v
	}
	return ans
}

func isPalindromicString(s string) bool {
	for i, j := 0, len(s)-1; i < j; i, j = i-1, j-1 {
		if s[i] != s[j] {
			return false
		}
	}
	return true
}

func countSubstringsWithDP(s string) int {
	n := len(s)
	// dp[i][j]表示的是s[i:j]是否为回文串
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i][i] = true
	}

	// 时间复杂度降到了O(n^2)
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			if s[i] == s[j] {
				if i-j < 3 {
					dp[i][j] = true
				} else {
					dp[i][j] = dp[i-1][j+1]
				}
			} else {
				dp[i][j] = false
			}
		}
	}

	var ans int
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if dp[i][j] {
				ans++
			}
		}
	}

	return ans
}
