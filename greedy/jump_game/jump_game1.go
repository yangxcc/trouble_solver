package jumpgame

// 用例通过数 169/170， bad case [4,2,0,0,1,1,4,4,4,0,4,0]
func canJumpWrongWay(nums []int) bool {
	n := len(nums) - 1
	for i := 0; i <= n; {
		steps := nums[i]
		// [i, steps+i]这个区间内的最大数
		maxSteps := 0
		// 这里需要保存下i的值
		tmp := i
		for j := tmp + 1; j <= steps+tmp; j++ {
			// 这里要>=，即使是等于，也尽量往后走
			if nums[j] >= maxSteps {
				maxSteps = nums[j]
				i = j
			}
		}
		if maxSteps == 0 {
			return false
		}
		if maxSteps+i >= n {
			return true
		}
	}
	return false
}

// 贪心的策略其实是一步步的走，不断统计最大能够覆盖的范围
func canJump(nums []int) bool {
	if len(nums) <= 1 {
		return true
	}
	var cover int
	for i := 0; i <= cover; i++ {
		if cover < nums[i]+i {
			cover = nums[i] + i
		}
		if cover >= len(nums)-1 {
			return true
		}
	}
	return false
}
