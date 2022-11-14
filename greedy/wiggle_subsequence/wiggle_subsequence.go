package wigglesubsequence

// leetcode 376 https://leetcode.cn/problems/wiggle-subsequence/
/**
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。
仅有一个元素或者含两个不等元素的序列也视作摆动序列。

例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。

子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
*/
func wiggleMaxLength(nums []int) int {
	ans := 1 // 默认最右边有一个峰值
	preDiff, curDiff := 0, 0
	for i := 0; i < len(nums)-1; i++ {
		curDiff = nums[i+1] - nums[i]
		if (preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0) {
			ans++
			preDiff = curDiff
		}
	}
	return ans
}

// 第一种思路可以 回溯+判断，因为回溯是穷举，能够把所有的路径都弄出来
// 贪心的理解：上述代码不太好理解，可见：https://programmercarl.com/0376.%E6%91%86%E5%8A%A8%E5%BA%8F%E5%88%97.html#%E6%80%9D%E8%B7%AF1-%E8%B4%AA%E5%BF%83%E8%A7%A3%E6%B3%95
// 这道题的另一个解法是通过动态规划
/**
假设dp数组状态为
dp[i][0] 第i个元素作为山峰时最长子序列
dp[i][1] 第i个元素作为山谷时最长子序列
转移方程为：
dp[i][0] = max(dp[j][1]+1, dp[i][0]), 0 < j < i && nums[j] < nums[i]
dp[i][1] = max(dp[j][0]+1, dp[i][1]), 0 < j < i && nums[j] > nums[i]
*/
func wiggleMaxLengthWithDP(nums []int) int {
	dp := make([][]int, len(nums))
	for i, _ := range dp {
		dp[i] = make([]int, 2)
	}

	dp[0][0], dp[0][1] = 1, 1
	for i := 1; i < len(nums); i++ {
		dp[i][0], dp[i][1] = 1, 1
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				dp[i][0] = max(dp[j][1]+1, dp[i][0])
			} else if nums[j] > nums[i] {
				dp[i][1] = max(dp[j][0]+1, dp[i][1])
			}
		}
	}
	return max(dp[len(nums)-1][0], dp[len(nums)-1][1])
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
