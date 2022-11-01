package pathandsum

import "trouble_solver/tree"

// 根节点到叶子节点的路径上所有节点和是否等于targetSum
func hasPathSum(root *tree.TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}
	var curSum int

	var process func(root *tree.TreeNode, targetSum int, curSum int) bool
	process = func(root *tree.TreeNode, targetSum, curSum int) bool {
		if root == nil {
			return false
		}
		curSum += root.Val
		if root.Left == nil && root.Right == nil && curSum == targetSum {
			return true
		}

		return process(root.Left, targetSum, curSum) || process(root.Right, targetSum, curSum)
	}

	return process(root, targetSum, curSum)
}

// 但是实际上，一般有target的题目，我们可以不用定义新变量，通过target==0来判断
func hasPathSumBetter(root *tree.TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}

	if root.Left == nil && root.Right == nil && targetSum-root.Val == 0 {
		return true
	}

	return hasPathSumBetter(root.Left, targetSum-root.Val) || hasPathSumBetter(root.Right, targetSum-root.Val)
}

// 可以看出，这实际上是前序遍历，因此也可以使用栈来非递归写出来
