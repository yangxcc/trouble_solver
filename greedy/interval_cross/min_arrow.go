package intervalcross

import "sort"

/**
本题是leetcode452，题目说的很邪乎，其实就是区间相交问题
*/
func findMinArrowShots(points [][]int) int {
	// 按照Xstart升序排序，Xstart相同的时候，按照Xend升序排序
	sort.Slice(points, func(i, j int) bool {
		if points[i][0] == points[j][0] {
			return points[i][1] < points[j][1]
		}
		return points[i][0] < points[j][0]
	})

	// 如果后一个区间的start在前一个区间的end里面，就说明是交叉的，这种情况下，只需要一只箭
	var interval = []int{points[0][0], points[0][1]}
	var ans int = 1
	for i := 1; i < len(points); i++ {
		if points[i][0] <= interval[1] {
			// 更新区间范围
			interval[0] = points[i][0]
			interval[1] = min(points[i][1], interval[1])
		} else {
			// 这里也得更新区间范围
			interval = points[i]
			ans++
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
