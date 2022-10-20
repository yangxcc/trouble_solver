package traversal

import (
	"trouble_solver/tree"
)

// 层序遍历
func sequenceTraversal(root *tree.TreeNode) [][]int {
	if root == nil {
		return [][]int{}
	}

	var ans [][]int
	q := tree.Queue{}
	q.Push(root)

	tmp := []int{}
	for !q.Empty() {
		size := q.Length()
		for size > 0 {
			node := q.Pop()
			tmp = append(tmp, node.Val)
			size--

			if node.Left != nil {
				q.Push(node.Left)
			}
			if node.Right != nil {
				q.Push(node.Right)
			}
		}
		ans = append(ans, tmp)
		// 清空tmp
		tmp = []int{}
	}
	return ans
}
