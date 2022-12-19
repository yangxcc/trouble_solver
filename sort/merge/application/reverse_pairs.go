/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-19 14:47:37
 * @LastEditTime: 2022-12-19 15:49:40
 */
package application

/**
题目描述
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
输入一个数组，求出这个数组中的逆序对的总数。
*/
func reversePairs(nums []int) int {
	if len(nums) <= 1 {
		return 0
	}
	var count int
	tmp := make([]int, len(nums))
	var merge func(nums []int, left, mid, right int)
	merge = func(nums []int, left, mid, right int) {
		for k := left; k <= right; k++ {
			tmp[k] = nums[k]
		}

		i, j := left, mid+1
		for p := left; p <= right; p++ {
			if i == mid+1 {
				nums[p] = tmp[j]
				j++
			} else if j == right+1 {
				nums[p] = tmp[i]
				i++
			} else if tmp[i] > tmp[j] {
				nums[p] = tmp[j]
				// [i,mid]即i后面的这些数字肯定也是比j大，共有mid-i+1个
				count += mid - i + 1
				j++
			} else {
				nums[p] = tmp[i]
				i++
			}
		}
	}

	var mergeSort func(nums []int, left, right int)
	mergeSort = func(nums []int, left, right int) {
		if left < right {
			mid := left + (right-left)/2
			mergeSort(nums, left, mid)
			mergeSort(nums, mid+1, right)
			merge(nums, left, mid, right)
		}
	}

	mergeSort(nums, 0, len(nums)-1)
	return count
}
