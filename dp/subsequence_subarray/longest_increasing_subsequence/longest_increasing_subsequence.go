package longestincreasingsubsequence

/**
给你一个整数数组nums ，找到其中最长严格递增子序列的长度。
子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
*/
func lengthOfLIS(nums []int) int {
	n := len(nums)

	// dp[i]表示从0到i，且以i结尾的最长严格递增子序列
	dp := make([]int, n)
	for i := 0; i < n; i++ {
		dp[i] = 1
	}

	// 得有ans来接住最大的值，存在bad case，见test中
	var ans int = 1
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[i] > nums[j] {
				dp[i] = max(dp[i], dp[j]+1)
				ans = max(ans, dp[i])
			}
		}
	}

	// fmt.Println(dp)
	return ans
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
