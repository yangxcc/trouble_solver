package combination

import "sort"

/**
给定一个候选人编号的集合 candidates 和一个目标数 target ，
找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30

*/

func combinationSum2(candidates []int, target int) [][]int {
	var ans [][]int
	// 一定要排序
	sort.Ints(candidates)
	visited := make([]bool, len(candidates))

	var backtrack2 func(candidates []int, target, curSum int, path []int, idx int, visited []bool)
	backtrack2 = func(candidates []int, target, curSum int, path []int, idx int, visited []bool) {
		if curSum == target {
			ans = append(ans, copy(path))
			return
		} else if curSum > target {
			// 剪枝1
			return
		}

		for i := idx; i < len(candidates); i++ {
			// 剪枝2
			if curSum+candidates[i] > target {
				break
			}
			// 这种情况下表示的是同一层已经被访问过了
			if i > 0 && candidates[i-1] == candidates[i] && !visited[i-1] {
				continue
			}
			curSum += candidates[i]
			path = append(path, candidates[i])
			visited[i] = true

			// 保证了每个数字只用一次
			backtrack2(candidates, target, curSum, path, i+1, visited)

			curSum -= candidates[i]
			path = path[:len(path)-1]
			visited[i] = false
		}
	}

	backtrack2(candidates, target, 0, []int{}, 0, visited)
	return ans
}

func combinationSum2WithoutVisied(candidates []int, target int) [][]int {
	var ans [][]int
	sort.Ints(candidates)

	var backtrack3 func(candidates []int, target, curSum int, path []int, idx int)
	backtrack3 = func(candidates []int, target, curSum int, path []int, idx int) {
		if curSum == target {
			ans = append(ans, copy(path))
			return
		} else if curSum > target {
			return
		}

		for i := idx; i < len(candidates); i++ {
			if curSum > target {
				break
			}
			// 区别就在这里 i > idx
			if i > idx && candidates[i-1] == candidates[i] {
				continue
			}
			curSum += candidates[i]
			path = append(path, candidates[i])

			backtrack3(candidates, target, curSum, path, i+1)

			curSum -= candidates[i]
			path = path[:len(path)-1]
		}
	}

	backtrack3(candidates, target, 0, []int{}, 0)
	return ans
}
