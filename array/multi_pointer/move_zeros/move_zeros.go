/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-31 21:24:02
 * @LastEditTime: 2022-12-31 21:34:05
 */
package movezeros

/**
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。
*/
func moveZeroes(nums []int) {
	if len(nums) <= 1 {
		return
	}

	slow, fast := 0, 0
	for fast < len(nums) {
		if nums[fast] != 0 {
			nums[slow] = nums[fast]
			slow++
		}
		fast++
	}

	for i := slow; i < len(nums); i++ {
		nums[i] = 0
	}
}
