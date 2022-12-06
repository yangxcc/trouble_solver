package subsetpackage

/**
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
本质上是属于0-1背包的
*/
func canPartition(nums []int) bool {
	sum, n := 0, len(nums)
	for _, num := range nums {
		sum += num
	}
	// 和是奇数肯定是不能一分为二
	if sum%2 == 1 {
		return false
	}
	target := sum / 2
	// nums中的元素能否组合成target，和凑硬币又相似了起来，只不过这里每个数不是无限的
	// dp[i][j]表示前i个数能否组装出j
	dp := make([][]bool, n+1)
	for i, _ := range dp {
		dp[i] = make([]bool, target+1)
		dp[i][0] = true
	}
	// for i := 0; i <= target; i++ {
	// 	dp[0][i] = true
	// }
	// dp[0][i]应该是false，没有数当然组装不出任何的target
	for i := 1; i <= n; i++ {
		for j := 1; j <= target; j++ {
			if j-nums[i-1] < 0 {
				dp[i][j] = dp[i-1][j]
			} else {
				dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]
			}
		}
	}
	return dp[n][target]
}
