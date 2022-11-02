package bst

import (
	"math"
	"trouble_solver/tree"
)

// 对于bst，中序遍历的结果是升序排序的数组，因此我们当然可以先通过中序遍历将所有节点的值拿到，然后依次算出最小值
// 但更好的方法还是在遍历过程中就拿到最小值
func getMinimumDifference(root *tree.TreeNode) int {
	ans := math.MaxInt64
	var pre *tree.TreeNode // 记录前一个节点
	var inOrder func(root *tree.TreeNode)
	inOrder = func(root *tree.TreeNode) {
		if root == nil {
			return
		}
		inOrder(root.Left)
		if pre != nil && root.Val-pre.Val < ans {
			ans = root.Val - pre.Val
		}
		pre = root
		inOrder(root.Right)
	}
	inOrder(root)
	return ans
}
