package subset

/**
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
*/
func subsets(nums []int) [][]int {
	if len(nums) == 0 {
		return [][]int{}
	}

	ans := [][]int{}
	var backtrack func(nums []int, idx int, path []int)
	backtrack = func(nums []int, idx int, path []int) {
		ans = append(ans, copy(path))

		for i := idx; i < len(nums); i++ {
			path = append(path, nums[i])

			backtrack(nums, i+1, path)

			path = path[:len(path)-1]
		}
	}
	backtrack(nums, 0, []int{})
	return ans
}

func copy(a []int) (b []int) {
	b = append(b, a...)
	return
}
