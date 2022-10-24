package level_traversal

import "trouble_solver/tree"

/**
二叉树的最大深度和最小深度都可以使用层序遍历来完成
也都可以使用递归方法来实现
*/
func minDepth(root *tree.TreeNode) int {
	if root == nil {
		return 0
	}
	q := []*tree.TreeNode{}
	q = append(q, root)
	var count int
	for len(q) > 0 {
		size := len(q)
		count++
		for size > 0 {
			node := q[0]
			q = q[1:]
			if node.Left == nil && node.Right == nil {
				return count
			}
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
			size--
		}
	}
	return -1
}

func minDepthWithRecusive(root *tree.TreeNode) int {
	if root == nil {
		return 0
	}
	if root.Left == nil && root.Right == nil {
		return 1
	}
	if root.Left == nil {
		return minDepthWithRecusive(root.Right) + 1
	}
	if root.Right == nil {
		return minDepthWithRecusive(root.Left) + 1
	}
	leftDepth := minDepthWithRecusive(root.Left)
	rightDepth := minDepthWithRecusive(root.Right)
	if leftDepth < rightDepth {
		return leftDepth + 1
	}
	return rightDepth + 1
}

// 最大深度
func maxDepth(root *tree.TreeNode) int {
	if root == nil {
		return 0
	}
	q := []*tree.TreeNode{}
	q = append(q, root)
	var count int
	for len(q) > 0 {
		size := len(q)
		count++
		for size > 0 {
			node := q[0]
			q = q[1:]
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
			size--
		}
	}
	return count
}

func maxDepthWithRecusive(root *tree.TreeNode) int {
	if root == nil {
		return 0
	}
	leftDepth := maxDepthWithRecusive(root.Left)
	rightDepth := maxDepthWithRecusive(root.Right)
	if leftDepth > rightDepth {
		return leftDepth + 1
	}
	return rightDepth + 1
}
