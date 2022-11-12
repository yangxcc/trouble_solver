package permutations

/**
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
*/
func permute(nums []int) [][]int {
	if len(nums) == 0 {
		return [][]int{}
	}

	ans := [][]int{}
	var backtrack1 func(nums []int, path []int)
	backtrack1 = func(nums, path []int) {
		if len(path) == len(nums) {
			ans = append(ans, copy(path))
			return
		}

		for i := 0; i < len(nums); i++ {
			if contains(path, nums[i]) {
				continue
			}
			path = append(path, nums[i])

			backtrack1(nums, path)

			path = path[:len(path)-1]
		}
	}

	backtrack1(nums, []int{})
	return ans
}

func copy(a []int) (b []int) {
	b = append(b, a...)
	return
}

func contains(path []int, target int) bool {
	for _, v := range path {
		if v == target {
			return true
		}
	}
	return false
}
