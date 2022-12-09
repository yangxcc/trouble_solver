/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 19:28:18
 * @LastEditTime: 2022-12-09 19:55:09
 */
package longestcommonsubarray

import "fmt"

/**
题目描述
给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
*/
func findLength(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	// dp[i][j]表示的是nums1[0...i]和nums2[0...j]的最长公共子数组
	dp := make([][]int, m+1)
	for i, _ := range dp {
		dp[i] = make([]int, n+1)
	}

	var ans int
	// base case dp[i][0]和dp[0][i]都是0
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if nums1[i-1] == nums2[j-1] {
				dp[i][j] = 1 + dp[i-1][j-1]
				// 这里一定要有这个ans来接，因为dp数组的定义，包含了以i和j结尾这一个条件，
				// 其实从nums1[i-1] == nums2[j-1] 判断中也能够看出来
				ans = max(ans, dp[i][j])
			}
		}
	}
	fmt.Println(dp)
	return ans
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
