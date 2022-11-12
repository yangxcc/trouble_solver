package combination

/**
给定一个候选人编号的集合 candidates 和一个目标数 target ，
找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的同一个数字可以无限制重复被选取
candidates 中没有重复元素
*/
func CombinationSum(candidates []int, target int) [][]int {
	if len(candidates) == 0 {
		return [][]int{}
	}
	ans := [][]int{}
	var backtrack4 func(candidates []int, target int, path []int, index int)
	backtrack4 = func(candidates []int, target int, path []int, index int) {
		if target == 0 {
			ans = append(ans, copy(path))
			return
		}
		if target < 0 {
			return
		}

		for i := index; i < len(candidates); i++ {
			path = append(path, candidates[i])
			target -= candidates[i]

			backtrack4(candidates, target, path, i)

			path = path[:len(path)-1]
			target += candidates[i]
		}
	}

	backtrack4(candidates, target, []int{}, 0)
	return ans
}
