package reconstructqueue

import "sort"

/**
假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）

输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
*/
func reconstructQueue(people [][]int) [][]int {
	// 先按照hi从大到小排列，如果高度相同，按照kj从小到大排列
	sort.Slice(people, func(i, j int) bool {
		if people[i][0] == people[j][0] {
			return people[i][1] < people[j][1]
		}
		return people[i][0] > people[j][0]
	})

	// [7,0],[7,1],[6,1],[5,0],[5,2],[4,4]
	// 按照k的值插入到相应位置，比如[5,2]就插入到idx=2这个位置
	ans := make([][]int, 0)
	for _, elem := range people {
		ans = append(ans, elem)
		// 将elem[1]位置的数空出来，向后错一个位置
		copy(ans[elem[1]+1:], ans[elem[1]:])
		ans[elem[1]] = elem // 插入元素
	}
	return ans
}

// 这道题目最开始没有想到，记得多看看
