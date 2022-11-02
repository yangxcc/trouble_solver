package ancestor

import "trouble_solver/tree"

func lowestCommonAncestor(root, p, q *tree.TreeNode) *tree.TreeNode {
	if root == nil || q == nil || q == nil {
		return nil
	}

	parent := map[*tree.TreeNode]*tree.TreeNode{}
	parent[root] = nil
	var findParent func(root *tree.TreeNode)
	findParent = func(root *tree.TreeNode) {
		if root == nil {
			return
		}
		if root.Left != nil {
			parent[root.Left] = root
			findParent(root.Left)
		}
		if root.Right != nil {
			parent[root.Right] = root
			findParent(root.Right)
		}
	}

	findParent(root)
	// 将p/q的路径拿出来
	qPath := map[*tree.TreeNode]bool{}
	tmp := q
	for parent[tmp] != nil {
		qPath[tmp] = true
		tmp = parent[tmp]
	}

	// 看一下q的路径上是否有p的父节点
	tmp = p
	for parent[tmp] != nil {
		if qPath[tmp] {
			return tmp
		}
		tmp = parent[tmp]
	}

	return root
}

// 找公共祖先需要自底向上，后序遍历就是自底向上！！
func lowestCommonAncestor2(root, p, q *tree.TreeNode) *tree.TreeNode {
	if root == nil || root == p || root == q {
		return root
	}

	// 这里lowestCommonAncestor2的含义可以看作是在树中找节点p/q
	left := lowestCommonAncestor2(root.Left, p, q)
	right := lowestCommonAncestor2(root.Right, p, q)
	if left != nil && right == nil {
		return left
	} else if left == nil && right != nil {
		return right
	} else if left != nil && right != nil {
		return root
	} else {
		return nil
	}
}
