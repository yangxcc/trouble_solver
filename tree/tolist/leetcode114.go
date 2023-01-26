/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-26 09:07:56
 * @LastEditTime: 2023-01-26 09:11:13
 */
package tolist

import "trouble_solver/tree"

func flatten(root *tree.TreeNode) {
	if root == nil {
		return
	}

	if root.Left == nil && root.Right == nil {
		return
	}

	flatten(root.Left)
	flatten(root.Right)

	rightTmp, cur := root.Right, root
	cur.Right = cur.Left
	cur.Left = nil
	for cur.Right != nil {
		cur = cur.Right
	}
	cur.Right = rightTmp
}
