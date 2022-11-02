package ancestor

import "trouble_solver/tree"

func lowestCommonAncestorInBST(root, p, q *tree.TreeNode) *tree.TreeNode {
	if root == nil || root == q || root == p {
		return root
	}

	if (root.Val-p.Val)*(root.Val-q.Val) < 0 {
		return root
	} else if root.Val > p.Val && root.Val > q.Val {
		return lowestCommonAncestorInBST(root.Left, p, q)
	} else {
		return lowestCommonAncestorInBST(root.Right, p, q)
	}
}

func lowestCommonAncestorInBST2(root, p, q *tree.TreeNode) *tree.TreeNode {
	if root == nil && root == p && root == q {
		return root
	}

	for root != nil {
		// 这里一定要有等于！！
		if (root.Val-p.Val)*(root.Val-q.Val) <= 0 {
			return root
		} else if root.Val > p.Val && root.Val > q.Val {
			root = root.Left
		} else if root.Val < p.Val && root.Val < q.Val {
			root = root.Right
		}
	}
	return nil
}
