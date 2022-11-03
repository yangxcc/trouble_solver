package bst

import (
	"trouble_solver/tree"
)

func convertBST(root *tree.TreeNode) *tree.TreeNode {
	if root == nil {
		return nil
	}

	var curSum int
	var inOrder func(root *tree.TreeNode)
	inOrder = func(root *tree.TreeNode) {
		if root == nil {
			return
		}
		// 因为sum是从小到大，所以这里要先遍历右子树
		inOrder(root.Right)
		curSum += root.Val
		inOrder(root.Left)
	}
	inOrder(root)
	return root
}
