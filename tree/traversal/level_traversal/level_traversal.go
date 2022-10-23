package level_traversal

import (
	"trouble_solver/tree"
)

// 层序遍历
func levelTraversal(root *tree.TreeNode) [][]int {
	if root == nil {
		return [][]int{}
	}

	var ans [][]int
	q := tree.Queue[tree.TreeNode]{}
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

func levelTraversalRecursive(root *tree.TreeNode) [][]int {
	ans := [][]int{}
	depth := 0

	var order func(root *tree.TreeNode, depth int)
	order = func(root *tree.TreeNode, depth int) {
		if root == nil {
			return
		}
		if len(ans) == depth {
			ans = append(ans, []int{})
		}
		ans[depth] = append(ans[depth], root.Val)

		order(root.Left, depth+1)
		order(root.Right, depth+1)
	}

	order(root, depth)

	return ans
}
