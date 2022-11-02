package bst

import "trouble_solver/tree"

func isValidBSTWrong(root *tree.TreeNode) bool {
	if root == nil {
		return true
	}
	if root.Left != nil && root.Left.Val >= root.Val {
		return false
	}
	if root.Right != nil && root.Right.Val <= root.Val {
		return false
	}

	return isValidBSTWrong(root.Left) && isValidBSTWrong(root.Right)
}

// 上面这种写法明显是错误的，因为它只能够判断一层，比如下面这个bad case
/**
				5
			/		\
     	  1			 8
					/ \
					6	10
*/
// 但是我意识到这个错误之后，想的是怎么找到左子树的最大值以及右子树的最小值
// 但却没有想到左子树的最大值也就是root了，右子树的最小值也是root

func isValidBST(root *tree.TreeNode) bool {
	if root == nil {
		return true
	}
	var valid func(root *tree.TreeNode, min *tree.TreeNode, max *tree.TreeNode) bool
	valid = func(root *tree.TreeNode, min *tree.TreeNode, max *tree.TreeNode) bool {
		if root == nil {
			return true
		}
		if min != nil && root.Val <= min.Val {
			return false
		}
		if max != nil && root.Val >= max.Val {
			return false
		}
		return valid(root.Left, min, root) && valid(root.Right, root, max)
	}

	return valid(root, nil, nil)
}
