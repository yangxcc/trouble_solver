package jumpgame

// 与jump_game1相比，这里是求到最后一个位置的最少次数
// 这里有一个假设，总能到达最后的一个位置
// 以最小的步数增加覆盖范围
// 贪心写法没懂！！
func jump(nums []int) int {
	// dp[i]表示的是到达i最少次数
	dp := make([]int, len(nums))
	dp[0] = 0
	for i := 1; i < len(nums); i++ {
		dp[i] = i
		for j := 0; j < i; j++ {
			if nums[j]+j >= i { // 从j这个位置能够跳到i
				dp[i] = min(dp[j]+1, dp[i]) // 更新dp[i]
			}
		}
	}
	return dp[len(nums)-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
