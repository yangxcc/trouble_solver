package bst

import "trouble_solver/tree"

func treeToDoublyList(root *tree.TreeNode) *tree.TreeNode {
	var pre, head *tree.TreeNode

	var inOrder func(cur *tree.TreeNode)
	inOrder = func(cur *tree.TreeNode) {
		if cur == nil {
			return
		}

		inOrder(cur.Left)

		if pre == nil {
			head = cur
		} else {
			pre.Right = cur
		}

		cur.Left = pre
		pre = cur

		inOrder(cur.Right)
	}

	inOrder(root)

	// 头尾相连
	pre.Right = head
	head.Left = pre

	return head
}
