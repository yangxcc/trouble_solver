package bst

import "trouble_solver/tree"

func deleteNode(root *tree.TreeNode, key int) *tree.TreeNode {
	if root == nil {
		return nil
	}

	if root.Val == key {
		if root.Left == nil {
			return root.Right
		} else if root.Right == nil {
			return root.Left
		} else {
			// 两边都不为空，选择右子树的最左侧
			node := root.Right
			// 上面判断过了，cur不可能为nil
			for node.Left != nil {
				node = node.Left
			}
			// 循环过后，cur就是替换的节点
			node.Right = deleteNode(root.Right, node.Val) // 这里一定要删除，再进行后面的操作
			node.Left = root.Left
			return node
		}
	}

	if root.Val < key {
		root.Left = deleteNode(root.Left, key)
	} else {
		root.Right = deleteNode(root.Right, key)
	}
	return root
}
