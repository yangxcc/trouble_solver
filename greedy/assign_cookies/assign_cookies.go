package greedy

import (
	"math"
	"sort"
)

/**
对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
*/

// 思想就是每一块饼干都喂给最合适的
func findContentChildren(g []int, s []int) int {
	visited := make([]bool, len(g))
	for _, cookieSize := range s {
		// 用来记录这块饼干分配给那个孩子
		var idx int = -1
		var minDiff int = math.MaxInt32
		for i, childSize := range g {
			if visited[i] || cookieSize < childSize {
				continue
			}
			if cookieSize-childSize < minDiff {
				minDiff = cookieSize - childSize
				idx = i
			}
		}
		if idx != -1 {
			visited[idx] = true
		}
	}

	// 统计visited中true的数量
	ans := 0
	for _, b := range visited {
		if b {
			ans++
		}
	}
	return ans
}

// 想法是一样的，只是代码更简洁，更快
// 从leetcode上看，时间并没有提高多少，内存使用还增加了，应该是两次排序比较费时间
func findContentChildrenBetterWay(g []int, s []int) int {
	sort.Ints(g)
	sort.Ints(s)
	index := len(g) - 1
	ans := 0
	for i := len(s) - 1; i >= 0; {
		// if s[i] >= g[index] {
		// 	ans++
		// 	index--
		// 	i--
		// } else {
		// 	// 换个胃口小的小孩
		// 	index--
		// }
		if s[i] >= g[index] {
			ans++
			i--
		}
		index--
	}

	return ans
}
