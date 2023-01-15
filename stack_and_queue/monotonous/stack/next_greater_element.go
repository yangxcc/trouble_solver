/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 10:02:40
 * @LastEditTime: 2023-01-15 11:42:04
 */
package stack

import "trouble_solver/stack_and_queue/container"

func NextGreaterElement(nums1 []int, nums2 []int) []int {
	m, n := len(nums1), len(nums2)
	memo, stack := map[int]int{}, container.Stack[int]{}

	nextGreater, ans := make([]int, n), make([]int, m)

	for i := n - 1; i >= 0; i-- {
		for !stack.Empty() && stack.Top() <= nums2[i] {
			stack.Pop()
		}

		if stack.Empty() {
			nextGreater[i] = -1
		} else {
			nextGreater[i] = stack.Top()
		}

		stack.Push(nums2[i])
		memo[nums2[i]] = i
	}

	for i := 0; i < m; i++ {
		ans[i] = nextGreater[memo[nums1[i]]]
	}

	return ans
}
