/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 17:27:04
 * @LastEditTime: 2022-12-13 17:42:42
 */
package sort

func Swap(nums []int, i, j int) {
	tmp := nums[i]
	nums[i] = nums[j]
	nums[j] = tmp
}
