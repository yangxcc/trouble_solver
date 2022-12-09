/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 10:56:28
 * @LastEditTime: 2022-12-09 15:46:31
 */
package robber

/**
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
*/
// 可以看到，与打家劫舍问题1不同之处在于 所有的房屋都围成了一圈，其实就是最后一间房是与第一间房相邻的
func rob2(nums []int) int {
	// 在这道题目中根据题意，只有三种情况：第一间房被抢最后一间房不被抢，第一间房不被抢最后一间房被抢，或者是第一和最后都不被抢
	// 很明显，第一间和最后一间不被抢是最小的，直接忽略即可
	if len(nums) == 1 {
		return nums[0]
	}
	return max(rob1Better(nums[1:]), rob1Better(nums[:len(nums)-1]))
}

func rob2WithRecursion(nums []int) int {
	if len(nums) == 1 {
		return nums[0]
	}
	// 在[start:end]范围内，能够盗窃到的最大值
	var process func(nums []int, start, end int) int
	process = func(nums []int, start, end int) int {
		if start > end {
			return 0
		}
		if start == end {
			return nums[start]
		}

		return max(nums[start]+process(nums, start+2, end), process(nums, start+1, end))
	}

	return max(process(nums, 0, len(nums)-2), process(nums, 1, len(nums)-1))
}
