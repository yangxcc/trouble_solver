/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-02 12:30:05
 * @LastEditTime: 2022-12-09 21:42:42
 */
package longestcommonsubsequence

import "fmt"

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

/**
leetcode 392 判断子序列
	判断s是否为t的子序列
*/
// 最长公共子序列的变种，这里的LCS=len(s) return true
func isSubsequence(s string, t string) bool {
	m, n := len(s), len(t)
	dp := make([][]int, m+1)
	for i, _ := range dp {
		dp[i] = make([]int, n+1)
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s[i-1] == t[j-1] {
				dp[i][j] = 1 + dp[i-1][j-1]
			} else {
				// 这里就没必要max(dp[i][j-1], dp[i-1][j])，因为s肯定是不用动的
				dp[i][j] = dp[i][j-1]
			}
		}
	}
	return dp[m][n] == m
}

/**但是对于leetcode 392这道题，题目中有一个进阶问法，如下：
如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，
你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码
*/
// 如果有很多的s，那么我们就要先用一些时间将长字符串中的数据 提取出来，然后再去匹配
// 类似于将t中的数据存到数据库中，以后再来s，直接去匹配就好了，不用再双重循环了
func isSubsequenceWithMassiveStr(s string, t string) bool {
	t = " " + t // //预处理，保证t[0] 也被正确表示，即memo[0][..]
	n := len(t)
	// 建立一个n*26的数组，示每个位置上26个字符下一次出现的位置
	memo := make([][]int, n)
	for i, _ := range memo {
		memo[i] = make([]int, 26)
	}

	// ahbgdc
	for i := 0; i < 26; i++ {
		nextPos := -1
		for j := n - 1; j >= 0; j-- {
			memo[j][i] = nextPos
			if int(t[j]-'a') == i {
				nextPos = j
			}
		}
	}

	for i, _ := range memo {
		fmt.Printf("%v: %v \n", string(t[i]), memo[i])
	}
	// 真正的场景中我们会先把memo存起来了

	idx := 0
	for i, _ := range s {
		idx = memo[idx][int(s[i]-'a')]
		if idx == -1 {
			return false
		}
	}
	return true
}
