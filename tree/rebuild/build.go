package rebuild

import (
	"trouble_solver/tree"
)

// 中序遍历和后序遍历重构树
func buildTree(inorder []int, postorder []int) *tree.TreeNode {
	if len(inorder) != len(postorder) {
		return nil
	}
	if len(inorder) == 0 || len(postorder) == 0 {
		return nil
	}

	rootval := postorder[len(postorder)-1]
	var rootIndex int
	for i, v := range inorder {
		if v == rootval {
			rootIndex = i
		}
	}

	return &tree.TreeNode{Val: rootval,
		Left:  buildTree(inorder[:rootIndex], postorder[:rootIndex]),
		Right: buildTree(inorder[rootIndex+1:], postorder[rootIndex:len(postorder)-1])}
}
