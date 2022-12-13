/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 17:52:44
 * @LastEditTime: 2022-12-13 18:04:17
 */
package selectSort

import utils "trouble_solver/sort"

func SelectSort(nums []int) []int {
	if len(nums) <= 1 {
		return nums
	}
	min := -1
	for i := 0; i < len(nums); i++ {
		min = i
		for j := i + 1; j < len(nums); j++ {
			if nums[min] > nums[j] {
				min = j
			}
		}
		if i != min {
			utils.Swap(nums, min, i)
		}
	}

	return nums
}
