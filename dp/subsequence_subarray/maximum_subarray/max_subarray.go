package maximumsubarray

func maxSubArray(nums []int) int {
	n := len(nums)
	// dp[i]表示0-i,以i结尾的最大子数组和，子数组与子序列不同的是，子数组必须是连续的
	dp := make([]int, n)
	dp[0] = nums[0]
	ans := nums[0]
	for i := 1; i < n; i++ {
		dp[i] = max(dp[i-1]+nums[i], nums[i])
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
