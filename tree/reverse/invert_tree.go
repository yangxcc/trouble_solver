package reverse

import "trouble_solver/tree"

func invertTree(root *tree.TreeNode) *tree.TreeNode {
	if root == nil {
		return nil
	}
	if root.Left == nil && root.Right == nil {
		return root
	}
	leftTree := invertTree(root.Left)
	rightTree := invertTree(root.Right)
	root.Left, root.Right = rightTree, leftTree
	return root
}
