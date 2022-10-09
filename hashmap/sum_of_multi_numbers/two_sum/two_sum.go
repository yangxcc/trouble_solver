package twosum

import "sort"

func twoSum(nums []int, target int) []int {
	m := map[int]int{}
	for k, v := range nums {
		if _, ok := m[v]; ok {
			return []int{k, m[v]}
		}
		m[target-v] = k
	}
	return []int{}
}

// 这道题之前做过很多次了，但是重新做又错了
// twoSumWrongMethod做法错误的点在于排序之后，数组中数字的位置发生了变化
func twoSumWrongMethod(nums []int, target int) []int {
	sort.Slice(nums, func(i, j int) bool {
		return nums[i] < nums[j]
	})

	for i, j := 0, len(nums)-1; i <= j; {
		if nums[i]+nums[j] == target {
			return []int{i, j}
		} else if nums[i]+nums[j] < target {
			i++
		} else {
			j--
		}
	}
	return []int{}
}

// 既然排序之后元素的位置发生了变化，那么我们可以使用一个map来保存排序前的元素-位置的对应关系
// 这样对于一些情况可以，但是对于数组中存在重复元素的情况下不行，比如nums=[3,3]
func twoSumWrongMethod2(nums []int, target int) []int {
	m := map[int]int{}
	for idx, v := range nums {
		m[v] = idx
	}

	sort.Slice(nums, func(i, j int) bool {
		return nums[i] < nums[j]
	})

	for i, j := 0, len(nums)-1; i <= j; {
		if nums[i]+nums[j] == target {
			return []int{m[nums[i]], m[nums[j]]}
		} else if nums[i]+nums[j] < target {
			i++
		} else {
			j--
		}
	}
	return []int{}
}
