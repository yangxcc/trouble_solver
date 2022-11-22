package intervalcross

import "sort"

/**
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
*/
func merge(intervals [][]int) [][]int {
	var ans = make([][]int, 0)
	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i][0] == intervals[j][0] {
			return intervals[i][1] < intervals[j][1]
		}
		return intervals[i][0] < intervals[j][0]
	})

	for i := 0; i < len(intervals); {
		var curInterval = []int{intervals[i][0], intervals[i][1]}
		i++
		for i < len(intervals) && intervals[i][0] <= curInterval[1] {
			curInterval[1] = max(intervals[i][1], curInterval[1])
			i++
		}
		ans = append(ans, curInterval)
	}
	return ans
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
