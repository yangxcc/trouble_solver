/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 19:46:09
 * @LastEditTime: 2022-12-19 15:13:34
 */
package merge

func MergeSort(nums []int) []int {
	if len(nums) <= 1 {
		return nums
	}

	tmp := make([]int, len(nums))
	return sortHelper(nums, tmp, 0, len(nums)-1)
}

func sortHelper(nums, tmp []int, left, right int) []int {
	if left < right {
		mid := left + (right-left)/2
		sortHelper(nums, tmp, left, mid)
		sortHelper(nums, tmp, mid+1, right)

		return merge(nums, tmp, left, mid, right)
	}
	return []int{}
}

func merge(nums, tmp []int, left, mid, right int) []int {
	// [left, mid]  [mid+1, right]两个数组归并
	idx, i, j := left, left, mid+1
	for i <= mid && j <= right {
		if nums[i] < nums[j] {
			tmp[idx] = nums[i]
			i++
		} else {
			tmp[idx] = nums[j]
			j++
		}
		idx++
	}

	for i <= mid {
		tmp[idx] = nums[i]
		i++
		idx++
	}

	for j <= right {
		tmp[idx] = nums[j]
		j++
		idx++
	}

	for k := left; k <= right; k++ {
		nums[k] = tmp[k]
	}

	return nums
}

func MergeSortBetter(nums []int) []int {
	if len(nums) <= 1 {
		return nums
	}

	tmp := make([]int, len(nums))

	var merge func(nums []int, left, mid, right int)
	merge = func(nums []int, left, mid, right int) {
		// 先将[left, right]这个区间内的数值拷贝到tmp中，以便能够直接将结果写入到nums中
		for k := left; k <= right; k++ {
			tmp[k] = nums[k]
		}

		i, j := left, mid+1
		for p := left; p <= right; p++ {
			if i == mid+1 {
				// 左边的已经全都合并完了
				tmp[p] = nums[j]
				j++
			} else if j == right+1 {
				tmp[p] = nums[i]
				i++
			} else if tmp[i] > tmp[j] {
				tmp[p] = nums[j]
				j++
			} else {
				tmp[p] = nums[i]
				i++
			}
		}
	}

	var sortHelper func(nums []int, left, right int)
	sortHelper = func(nums []int, left, right int) {
		if left < right {
			mid := left + (right-left)/2
			sortHelper(nums, left, mid)
			sortHelper(nums, mid+1, right)
			merge(nums, left, mid, right)
		}
	}

	sortHelper(nums, 0, len(nums)-1)
	return nums
}
