package pathandsum

import "trouble_solver/tree"

// 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
func PathSum(root *tree.TreeNode, targetSum int) [][]int {
	if root == nil {
		return [][]int{}
	}
	ans := make([][]int, 0)
	var process func(root *tree.TreeNode, targetSum int, curPath []int)
	process = func(root *tree.TreeNode, targetSum int, curPath []int) {
		if root == nil {
			return
		}
		curPath = append(curPath, root.Val)
		targetSum -= root.Val
		if root.Left == nil && root.Right == nil && targetSum == 0 {
			// 不能直接将currPath放到result里面, 因为currPath是共享的, 每次遍历子树时都会被修改（没太明白）
			// ans = append(ans, copySlice(curPath))
			ans = append(ans, copySlice(curPath))
			return
		}
		process(root.Left, targetSum, curPath)
		process(root.Right, targetSum, curPath)

		// TODO 好奇怪！！有没有下面这段回溯代码都是正确的？？？
		// length := len(curPath)
		// curPath = curPath[:length-1]
		// targetSum += root.Val
	}
	process(root, targetSum, []int{})
	return ans
}

func copySlice(a []int) []int {
	var ans []int
	for _, v := range a {
		ans = append(ans, v)
	}
	return ans
}
