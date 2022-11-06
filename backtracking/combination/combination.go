package combination

// https://programmercarl.com/0077.%E7%BB%84%E5%90%88.html#%E5%89%AA%E6%9E%9D%E4%BC%98%E5%8C%96 讲的很好

/**
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

你可以按 任何顺序 返回答案。
*/
func combine(n int, k int) [][]int {
	if n < k {
		return [][]int{}
	}
	ans := [][]int{}
	var backtrack func(n int, k int, path []int, index int)
	backtrack = func(n int, k int, path []int, index int) {
		if len(path) == k {
			// 比较疑惑的点在这里为什么要copy一下才可以？
			ans = append(ans, copy(path))
			return
		}
		for i := index; i <= n-(k-len(path))+1; i++ {
			path = append(path, i)
			backtrack(n, k, path, i+1)
			path = path[:len(path)-1]
		}
	}

	backtrack(n, k, []int{}, 1)
	return ans
}

func copy(a []int) (b []int) {
	b = append(b, a...)
	return b
}
