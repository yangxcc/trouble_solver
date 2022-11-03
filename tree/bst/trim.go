package bst

import "trouble_solver/tree"

// 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
func trimBST(root *tree.TreeNode, low int, high int) *tree.TreeNode {
	if root == nil {
		return nil
	}

	// 这种情况下只需要返回右子树
	if root.Val < low {
		return trimBST(root.Right, low, high)
	}
	if root.Val > high {
		return trimBST(root.Left, low, high)
	}

	root.Left = trimBST(root.Left, low, high)
	root.Right = trimBST(root.Right, low, high)
	return root
}
