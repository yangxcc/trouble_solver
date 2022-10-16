package usage

import "sort"

/*
	分为两步
		统计频率
		按照频率排序
*/
func topKFrequent(nums []int, k int) []int {
	m := map[int]int{}
	for _, v := range nums {
		m[v]++
	}

	// 根据map的value进行排序
	var ans []int
	for k, _ := range m {
		ans = append(ans, k)
	}

	// 难点在于排序，可以自己写快排，也可以使用堆
	sort.Slice(ans, func(i, j int) bool {
		return m[ans[i]]-m[ans[j]] > 0
	})

	return ans[0:k]
}

// TODO 实现小顶堆
