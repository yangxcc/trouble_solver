/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 10:59:58
 * @LastEditTime: 2023-01-15 11:19:19
 */
package stack

import . "trouble_solver/stack_and_queue/container"

func MonotonousStackTemplate(nums []int) []int {
	stack, n := Stack[int]{}, len(nums)
	ans := make([]int, n)

	for i := n - 1; i >= 0; i-- {
		for !stack.Empty() && stack.Top() <= nums[i] {
			stack.Pop()
		}

		if stack.Empty() {
			ans[i] = -1
		} else {
			ans[i] = stack.Top()
		}

		stack.Push(nums[i])
	}

	return ans
}
