package intervalcross

import "sort"

/**
本题区间排序使用的是区间的右边界，按照右边界升序排列，因为右边界越小，表示在右边留下的空间就越大
或者是按照区间的左边界排序，左边界越大，则表示在左边留下的空间就越大
*/
func eraseOverlapIntervals(intervals [][]int) int {
	// 根据区间的右边界升序排列
	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i][1] == intervals[j][1] {
			return intervals[i][0] < intervals[j][0]
		}
		return intervals[i][1] < intervals[j][1]
	})

	var nonCross int = 1
	var edge int = intervals[0][1]
	for i := 1; i < len(intervals); i++ {
		if intervals[i][0] >= edge {
			nonCross++
			edge = intervals[i][1]
		}
	}

	return len(intervals) - nonCross
}
