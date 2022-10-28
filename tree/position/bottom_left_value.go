package position

import "trouble_solver/tree"

func findBottomLeftValue(root *tree.TreeNode) int {
	if root == nil {
		return 0
	}
	// 层序遍历，最后一层的第一个元素
	q := []*tree.TreeNode{}
	q = append(q, root)

	var ans int
	for len(q) > 0 {
		size := len(q)
		for i := 0; i < size; i++ {
			node := q[0]
			q = q[1:]
			if i == 0 {
				// 记录每一层的第一个就好了
				ans = node.Val
			}
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
	}

	return ans
}

// TODO 这里dfs用到了回溯，后面再说...
