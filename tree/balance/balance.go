package balance

import "trouble_solver/tree"

// 完全是按照求高度的思路
func isBalanced(root *tree.TreeNode) bool {
	if root == nil {
		return true
	}
	leftHeight := height(root.Left)
	rightHeight := height(root.Right)
	if leftHeight-rightHeight > 1 || leftHeight-rightHeight < -1 {
		return false
	}
	return isBalanced(root.Left) && isBalanced(root.Right)
}

func height(root *tree.TreeNode) int {
	if root == nil {
		return 0
	}
	leftDepth := height(root.Left)
	rightDepth := height(root.Right)
	if leftDepth < rightDepth {
		return rightDepth + 1
	}
	return leftDepth + 1
}

func isBalancedBetterWay(root *tree.TreeNode) bool {
	if root == nil {
		return true
	}

	var process func(root *tree.TreeNode) int
	process = func(root *tree.TreeNode) int {
		if root == nil {
			return 0
		}
		leftHeight := process(root.Left)
		rightHeight := process(root.Right)
		// if leftHeight >= 0 && rightHeight >= 0 && (leftHeight-rightHeight <= 1 || leftHeight-rightHeight <= -1) {
		// 	return max(leftHeight, rightHeight) + 1
		// } else {
		// 	return -1
		// }
		if leftHeight >= 0 && rightHeight >= 0 && abs(leftHeight, rightHeight) <= 1 {
			return max(leftHeight, rightHeight) + 1
		} else {
			return -1
		}
	}

	return process(root) >= 0
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}

func abs(a, b int) int {
	if a < b {
		return b - a
	}
	return a - b
}
