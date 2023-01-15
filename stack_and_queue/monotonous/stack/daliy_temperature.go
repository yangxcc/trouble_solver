/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 16:54:24
 * @LastEditTime: 2023-01-15 17:24:37
 */
package stack

import "trouble_solver/stack_and_queue/container"

func dailyTemperatures(temperatures []int) []int {
	n := len(temperatures)
	stack, ans := container.Stack[int]{}, make([]int, n)

	for i := n - 1; i >= 0; i-- {
		for !stack.Empty() && temperatures[stack.Top()] <= temperatures[i] {
			stack.Pop()
		}

		if stack.Empty() {
			ans[i] = 0
		} else {
			ans[i] = stack.Top() - i
		}

		stack.Push(i)
	}

	return ans
}
