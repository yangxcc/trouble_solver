/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 20:40:34
 * @LastEditTime: 2022-12-19 16:24:00
 */
package application

/**
问题描述
给你一个整数数组 nums ，按要求返回一个新数组 counts 。
数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
*/
// 这道题是hard，所以肯定不是让我们使用暴力方法O(n^2)
// 这道题目和 剑指 Offer 51. 数组中的逆序对（reverse_pairs.go）不同的是：该题目需要记录下来当前的索引，因为返回的是一个数组
func countSmaller(nums []int) []int {
	if len(nums) <= 1 {
		return []int{0}
	}

	n := len(nums)
	tmp, pairs, count := make([]Pair, n), make([]Pair, n), make([]int, n)
	// 记录下初始时的索引和值
	for i := 0; i < n; i++ {
		pairs[i] = Pair{Idx: i, Val: nums[i]}
	}

	var merge func(nums []Pair, left, mid, right int)
	merge = func(nums []Pair, left, mid, right int) {
		// 先将[left, right]这个区间内的数值拷贝到tmp中，以便能够直接将结果写入到nums中
		for k := left; k <= right; k++ {
			tmp[k] = nums[k]
		}

		i, j := left, mid+1
		for p := left; p <= right; p++ {
			if i == mid+1 {
				// 左边的已经全都合并完了
				nums[p] = tmp[j]
				j++
			} else if j == right+1 {
				nums[p] = tmp[i]
				// [mid+1, j)区间内的所有元素都是比nums[i]小的元素
				count[pairs[p].Idx] += j - mid - 1
				i++
			} else if tmp[i].Val > tmp[j].Val {
				nums[p] = tmp[j]
				j++
			} else {
				nums[p] = tmp[i]
				// [mid+1, j)区间内的所有元素都是比nums[i]小的元素
				count[pairs[p].Idx] += j - mid - 1
				i++
			}
		}
	}

	var sortHelper func(nums []Pair, left, right int)
	sortHelper = func(nums []Pair, left, right int) {
		if left < right {
			mid := left + (right-left)/2
			sortHelper(pairs, left, mid)
			sortHelper(pairs, mid+1, right)
			merge(pairs, left, mid, right)
		}
	}

	sortHelper(pairs, 0, len(nums)-1)
	return count
}

type Pair struct {
	Idx int
	Val int
}
