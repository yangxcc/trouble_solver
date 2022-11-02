package bst

import "trouble_solver/tree"

func insertIntoBST(root *tree.TreeNode, val int) *tree.TreeNode {
	if root == nil {
		return &tree.TreeNode{Val: val}
	}

	if root.Val > val && root.Left == nil {
		root.Left = &tree.TreeNode{Val: val}
		return root
	}

	if root.Val < val && root.Right == nil {
		root.Right = &tree.TreeNode{Val: val}
		return root
	}

	if root.Val < val {
		insertIntoBST(root.Right, val)
	} else {
		insertIntoBST(root.Left, val)
	}
	return root
}

func insertIntoBST2(root *tree.TreeNode, val int) *tree.TreeNode {
	if root == nil {
		return &tree.TreeNode{Val: val}
	}
	if root.Val < val {
		root.Right = insertIntoBST2(root.Right, val)
	} else {
		root.Left = insertIntoBST2(root.Left, val)
	}
	return root
}

/**
从上面这两个递归写法中就能够看出不同递归函数的意义是不同的
我自己写的是第一种写法，这种写法递归函数的意义就是根据根节点与val大小的比较判断插入子树，因此不需要返回值
但明显这种方法不好，因为直观上看代码比较多，不够简洁，其次insertIntoBST有返回值，这一点没利用上

第二种写法我们使用root.left/root.right来接住insertIntoBST2的返回值，这其实也很好理解，比如
当 root.Val < val 时，我们会在右子树插入，而插入后的子树由root.Right接收
*/
