package bst

import "trouble_solver/tree"

// 这道题其实是在修改了bst概念的前提上展开的，仅在这道题目中定义了bst是左子树的值<=根节点的值，右子树的值>=根节点的值
// 这道题同样是使用bst中序遍历的性质
func findMode(root *tree.TreeNode) []int {
	if root == nil {
		return []int{}
	}
	maxCount, curCount := 0, 0
	var pre *tree.TreeNode
	ans := []int{}

	var inOrder func(root *tree.TreeNode)
	inOrder = func(root *tree.TreeNode) {
		if root == nil {
			return
		}
		inOrder(root.Left)

		if pre == nil {
			curCount = 1 // 第一个节点
		} else if pre != nil && pre.Val == root.Val {
			curCount++
		} else if pre != nil && pre.Val != root.Val {
			curCount = 1
		}

		pre = root

		if curCount > maxCount {
			ans = []int{}
			ans = append(ans, root.Val)
			maxCount = curCount
		} else if curCount == maxCount {
			ans = append(ans, root.Val)
		}

		inOrder(root.Right)
	}

	inOrder(root)
	return ans
}
