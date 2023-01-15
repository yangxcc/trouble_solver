/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 11:43:36
 * @LastEditTime: 2023-01-15 16:53:05
 */
package stack

import "trouble_solver/stack_and_queue/container"

func NextGreaterElements2(nums []int) []int {
	n := len(nums)
	stack, ans := container.Stack[int]{}, make([]int, n)

	for i := 2*n - 1; i >= 0; i-- {
		for !stack.Empty() && stack.Top() <= nums[i] {
			stack.Pop()
		}

		if stack.Empty() {
			ans[i%n] = -1
		} else {
			ans[i%n] = stack.Top()
		}

		stack.Push(nums[i%n])
	}
	return ans
}
