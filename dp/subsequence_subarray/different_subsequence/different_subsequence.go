/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-10 11:56:26
 * @LastEditTime: 2022-12-10 12:23:50
 */
package differentsubsequence

/**
题目描述
给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串
（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
题目数据保证答案符合 32 位带符号整数范围。
*/
// 如果是连续的话，或许可以使用滑动窗口
func numDistinct(s string, t string) int {
	m, n := len(s), len(t)
	// dp[i][j]表示的是字符串s以i结尾的子序列中 t以j结尾出现的次数
	dp := make([][]int, m+1)
	for i, _ := range dp {
		dp[i] = make([]int, n+1)
	}

	// 当t=""时，空字符串是每个字符串的子序列
	for i := 0; i <= m; i++ {
		dp[i][0] = 1
	}

	// // 当s=""时，s中不存在任何的子序列
	// for i := 0; i <= n; i++ {
	// 	dp[0][i] = 0
	// }
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s[i-1] == t[j-1] {
				// 这里比较难想到的点就在后面的dp[i-1][j]，case：s="bagg", t="bag"
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
			} else {
				// 只能是在s上动
				dp[i][j] = dp[i-1][j]
			}
		}
	}

	return dp[m][n]
}

// 这里我们dp[i][j]的定义是s[0..i]和t[0..j]，也可以定义为s[i..]和t[j..]
