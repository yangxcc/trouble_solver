package level_traversal

import (
	"trouble_solver/tree"
)

// 自底向上的层序遍历
func levelOrderBottom(root *tree.TreeNode) [][]int {
	// 在刷题时，go中没有现成的队列/栈，但是先push/pop操作并不麻烦
	// 比如队列的push   q = append(q, item)
	// pop  node := q[0]  q = q[1:]
	levelOrderTop := sequenceTraversal(root)
	return reverse2DimSlice(levelOrderTop)
}

func reverse2DimSlice(top [][]int) [][]int {
	bottom := [][]int{}
	for i := len(top) - 1; i >= 0; i-- {
		bottom = append(bottom, top[i])
	}
	return bottom
}
