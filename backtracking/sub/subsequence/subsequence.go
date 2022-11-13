package subsequence

/**
给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。

数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。

这里需要注意子序列不一定是连续的
*/
func findSubsequences(nums []int) [][]int {
	if len(nums) == 0 {
		return [][]int{}
	}

	ans := [][]int{}
	var backtrack func(nums, path []int, idx int)
	backtrack = func(nums, path []int, idx int) {
		if len(path) > 1 {
			ans = append(ans, copy(path))
			// return  这里就不能返回了，因为还需要继续往下走
		}

		visited := map[int]bool{}
		for i := idx; i < len(nums); i++ {
			// 不能这么写，因为数组不一定是有序的
			// if i > idx && nums[i-1] == nums[i] {
			// 	continue
			// }

			// 有两种情况跳过：一是当前位置的数比path最后一个小，二是 当前位置的数在本层已经出现过了
			if (len(path) > 0 && nums[i] < path[len(path)-1]) || visited[nums[i]] {
				continue
			}
			visited[nums[i]] = true
			path = append(path, nums[i])

			backtrack(nums, path, i+1)

			// 这里不再标记visited[nums[i]] = false，因为需要记录同一行中已经被访问过的数字
			path = path[:len(path)-1]
		}
	}
	backtrack(nums, []int{}, 0)
	return ans
}

func copy(a []int) (b []int) {
	b = append(b, a...)
	return
}
