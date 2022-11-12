package subset

import "sort"

/**
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排
*/
func subsetsWithDup(nums []int) [][]int {
	if len(nums) == 0 {
		return [][]int{}
	}

	sort.Ints(nums)
	ans := [][]int{}
	var backtrack2 func(nums, path []int, idx int)
	backtrack2 = func(nums, path []int, idx int) {
		ans = append(ans, copy(path))

		for i := idx; i < len(nums); i++ {
			if i > idx && nums[i-1] == nums[i] {
				continue
			}
			path = append(path, nums[i])
			backtrack2(nums, path, i+1)
			path = path[:len(path)-1]
		}
	}

	backtrack2(nums, []int{}, 0)
	return ans
}
