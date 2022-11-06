package combination

/**
找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：

只使用数字1到9
每个数字 最多使用一次
返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
*/
// 组合总数3和组合1很像
func combinationSum3(k int, n int) [][]int {
	if n < 1 || n > 45 {
		return [][]int{}
	}

	var ans [][]int
	var backtrack4 func(k int, n int, curSum int, path []int, idx int)
	backtrack4 = func(k, n int, curSum int, path []int, idx int) {
		if curSum == n && len(path) == k {
			// 一定要注意这里的copy，Java也一样！！！
			// 但是目前原因还未知 😔
			ans = append(ans, copy(path))
			return
		}

		for i := idx; i <= 9; i++ {
			if curSum > n || len(path) > k {
				continue
			}
			curSum += i
			path = append(path, i)

			backtrack4(k, n, curSum, path, i+1)

			curSum -= i
			path = path[:len(path)-1]
		}
	}

	backtrack4(k, n, 0, []int{}, 1)
	return ans
}
