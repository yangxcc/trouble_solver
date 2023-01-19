/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-23 16:58:24
 * @LastEditTime: 2023-01-19 14:27:11
 */
package level_traversal

import "trouble_solver/tree"

// 所谓右视图，其实就是层序遍历每一层的最后一个元素
func rightSideView(root *tree.TreeNode) []int {
	if root == nil {
		return []int{}
	}

	ans := []int{}
	queue := []*tree.TreeNode{}
	queue = append(queue, root)
	for len(queue) > 0 {
		size := len(queue) - 1
		for size >= 0 {
			node := queue[0]
			queue = queue[1:]
			if size == 0 {
				ans = append(ans, node.Val)
			}
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
			size--
		}
	}
	return ans
}
