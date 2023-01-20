/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-20 10:55:21
 * @LastEditTime: 2023-01-20 10:58:31
 */
package pathandsum

import "trouble_solver/tree"

func DiameterOfBinaryTree(root *tree.TreeNode) int {
	var ans int = -1
	var height func(root *tree.TreeNode) int

	height = func(root *tree.TreeNode) int {
		if root == nil {
			return 0
		}

		leftMaxHeight := height(root.Left)
		rightMaxHeight := height(root.Right)

		ans = max(ans, leftMaxHeight+rightMaxHeight)

		return max(leftMaxHeight, rightMaxHeight) + 1
	}

	height(root)

	return ans
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
