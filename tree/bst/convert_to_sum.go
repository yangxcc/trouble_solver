/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-03 17:27:21
 * @LastEditTime: 2023-02-01 10:36:35
 */
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
		root.Val = curSum
		inOrder(root.Left)
	}
	inOrder(root)
	return root
}
