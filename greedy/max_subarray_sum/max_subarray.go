package maxsubarraysum

import "math"

/**
值得注意的是：子数组和子序列不一样，子数组必须是连续的

受到上一道题目“摆动序列”的影响，我最先想到的竟然是dp方法
*/
// 贪心的思想：当连续子数组的和为负数时，立刻放弃
func maxSubArray(nums []int) int {
	ans := math.MinInt
	count := 0 // 用来记录子数组的和
	for i := 0; i < len(nums); i++ {
		count += nums[i]
		if count > ans {
			ans = count
		}
		if count < 0 {
			count = 0
		}
	}

	return ans
}

/**
dp数组的状态为：
dp[i][0] 表示选择第i个元素的子数组的和
dp[i][1] 表示不选择第i个元素的子数组的和
状态转移方程为
dp[i][0] = max(nums[i], dp[i-1][0]+nums[i])
dp[i][1] = max(dp[i-1][0], dp[i-1][1])
*/
// 这种写法是错误的，因为这样写存在都不选的问题，bad case [-1],[-2,-1]
// update：我们需要把dp[0][1]的值修改为math.MinInt，而不是0
func maxSubArrayWithDP1(nums []int) int {
	dp := make([][]int, len(nums))
	for i, _ := range dp {
		dp[i] = make([]int, 2)
	}

	dp[0][0], dp[0][1] = nums[0], math.MinInt
	for i := 1; i < len(nums); i++ {
		dp[i][0] = max(nums[i], dp[i-1][0]+nums[i])
		dp[i][1] = max(dp[i-1][0], dp[i-1][1])
	}
	return max(dp[len(nums)-1][0], dp[len(nums)-1][1])
}

// math包中的Max要求a,b是float64
func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}

/**
dp[i]表示以第i个元素结尾的最大子数组和
状态转移方程：
dp[i] = max(dp[i-1]+nums[i], nums[i])
*/
func maxSubArrayWithDP(nums []int) int {
	dp := make([]int, len(nums))
	dp[0] = nums[0]
	ans := dp[0]
	for i := 1; i < len(nums); i++ {
		dp[i] = max(dp[i-1]+nums[i], nums[i])
		if dp[i] > ans {
			ans = dp[i]
		}
	}
	return ans
}
