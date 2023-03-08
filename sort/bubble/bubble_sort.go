/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 15:24:06
 * @LastEditTime: 2023-02-23 16:33:32
 */
package bubble

import utils "trouble_solver/sort"

func BubbleSort(nums []int) []int {
	if len(nums) <= 1 {
		return nums
	}

	for i := 0; i < len(nums); i++ {
		for j := 0; j < len(nums)-i-1; j++ {
			if nums[j] > nums[j+1] {
				utils.Swap(nums, j, j+1)
			}
		}
	}
	return nums
}
