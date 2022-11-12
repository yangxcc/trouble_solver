package permutations

import "sort"

/**
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
*/
func permuteUnique(nums []int) [][]int {
	if len(nums) == 0 {
		return [][]int{}
	}

	sort.Ints(nums)

	ans := [][]int{}
	visited := make([]bool, len(nums))
	var backtrack2 func(nums []int, path []int, visited []bool)
	backtrack2 = func(nums, path []int, visited []bool) {
		if len(path) == len(nums) {
			ans = append(ans, copy(path))
			return
		}
		for i := 0; i < len(nums); i++ {
			if visited[i] {
				continue
			}
			if i > 0 && nums[i] == nums[i-1] && !visited[i-1] {
				continue
			}
			path = append(path, nums[i])
			visited[i] = true

			backtrack2(nums, path, visited)

			path = path[:len(path)-1]
			visited[i] = false
		}
	}

	backtrack2(nums, []int{}, visited)
	return ans
}
