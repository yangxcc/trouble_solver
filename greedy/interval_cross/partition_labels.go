package intervalcross

import (
	"fmt"
	"sort"
)

/**
字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。

输入：S = "ababcbacadefegdehijhklij"
输出：[9,7,8]
解释：
	划分结果为 "ababcbaca", "defegde", "hijhklij"。
	每个字母最多出现在一个片段中。
	像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
*/
func partitionLabels(s string) []int {
	// 第一步：统一每个字母最先出现和最后出现的位置,[]int数组分别表示 出现的次数、最先出现的位置、最后出现的位置
	record := map[byte][]int{}
	for i := 0; i < len(s); i++ {
		if _, ok := record[s[i]]; !ok {
			record[s[i]] = make([]int, 3)
		}
		record[s[i]][0]++
		if record[s[i]][0] == 1 {
			record[s[i]][1] = i
		} else {
			// 对于只出现一次的字母，其record[s[i]][2] == 0，这里需要注意
			record[s[i]][2] = i
		}
	}

	fmt.Println(record)

	// 第二步：根据得到的[start,end]进行区间合并
	var intervals [][]int
	for _, v := range record {
		tmp := []int{v[1], v[2]}
		intervals = append(intervals, tmp)
	}
	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i][0] == intervals[j][0] {
			return intervals[i][1] < intervals[j][1]
		}
		return intervals[i][0] < intervals[j][0]
	})

	var mergedInterval = [][]int{}
	for i := 0; i < len(intervals); i++ {
		if len(mergedInterval) == 0 {
			mergedInterval = append(mergedInterval, intervals[i])
			continue
		}

		for j := 0; j < len(mergedInterval); j++ {
			// mergedInterval[j]和intervals[i]是否能够合并，左边界选最小，右边界选最大
			if mergedInterval[j][1] > intervals[i][0] {
				mergedInterval[j][1] = max(mergedInterval[j][1], intervals[i][1])
				// 左边界选最小，其实不用动就行了，排序已经确定了
				break // 不需要继续往下了，也得益于排序，首先加入的肯定是最接近的
			}

			if j == len(mergedInterval)-1 {
				// 已经到了最后一个了
				mergedInterval = append(mergedInterval, intervals[i])
				break // 这里也要break，因为添加了intervals[i]之后，mergedIntervals的长度发生变化了
			}
		}
	}

	// 第三步：统计个数
	var ans []int // 用来记录每个区间的长度
	for _, interval := range mergedInterval {
		if interval[1] == 0 {
			ans = append(ans, 1)
		} else {
			ans = append(ans, interval[1]-interval[0]+1)
		}
	}

	fmt.Println(ans)
	return ans
}

/**
这种思路更加巧妙，步骤如下：
	1. 统计每个字母出现的最远距离
	2. 重新遍历字符串，如果当前位置正好是字母出现的最远距离，那么该位置便是分割点
		详见：https://programmercarl.com/0763.%E5%88%92%E5%88%86%E5%AD%97%E6%AF%8D%E5%8C%BA%E9%97%B4.html#%E6%80%9D%E8%B7%AF
*/
func partitionLabelsBetter(s string) []int {
	// 统计每个字母出现的最远距离(一个小技巧，涉及到字母的问题可以不用map，因为字母的ascii码是固定的，直接使用数组即可)
	record := make([]int, 26)
	for i := 0; i < len(s); i++ {
		record[s[i]-'a'] = i
	}

	left, right, ans := 0, 0, []int{}
	for i := 0; i < len(s); i++ {
		// 通过right记录下已经访问过的所有字母的最远距离
		right = max(right, record[s[i]-'a'])
		if right == i {
			ans = append(ans, right-left+1)
			left = right + 1
		}
	}
	return ans
}

// [0,8] 9
// [9,15] 7
// [16,23] 8
