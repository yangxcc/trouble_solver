/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 19:46:09
 * @LastEditTime: 2022-12-13 20:05:30
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
