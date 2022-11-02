package bst

import "trouble_solver/tree"

func searchBST(root *tree.TreeNode, val int) *tree.TreeNode {
	if root == nil || root.Val == val {
		return root
	}
	if root.Val < val {
		return searchBST(root.Right, val)
	}
	return searchBST(root.Left, val)
}

func searchBST2(root *tree.TreeNode, val int) *tree.TreeNode {
	if root == nil {
		return nil
	}

	for root != nil {
		if root.Val == val {
			return root
		} else if root.Val < val {
			root = root.Right
		} else {
			root = root.Left
		}
	}
	return nil
}

// 可以看到，不管是递归写法还是非递归写法，都非常简单，这是因为二叉搜索树的性质确定了每次遍历方向的有序性
// 左子树的值比根节点小，右子树的值比根节点大
