package count

import "trouble_solver/tree"

func countNodes(root *tree.TreeNode) int {
	if root == nil {
		return 0
	}

	// 如果不是完全二叉树，不需要写下面这一部分了
	var leftDepth, rightDepth int
	var leftPointer, rightPointer = root.Left, root.Right
	for leftPointer != nil {
		leftDepth++
		leftPointer = leftPointer.Left
	}
	for rightPointer != nil {
		rightDepth++
		rightPointer = leftPointer.Right
	}
	if leftDepth == rightDepth {
		return (2 << leftDepth) - 1
	}

	return countNodes(root.Left) + countNodes(root.Right) + 1
}
