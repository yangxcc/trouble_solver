/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 18:10:45
 * @LastEditTime: 2022-12-13 19:21:47
 */
package insert

func InsertSort(nums []int) []int {
	if len(nums) <= 1 {
		return nums
	}

	for i := 1; i < len(nums); i++ {
		curVal := nums[i]
		pos := i - 1

		for pos >= 0 && nums[pos] > curVal {
			nums[pos+1] = nums[pos]
			pos--
		}
		nums[pos+1] = curVal
	}
	return nums
}
