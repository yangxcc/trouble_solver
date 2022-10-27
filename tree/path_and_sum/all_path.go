package pathandsum

import (
	"fmt"
	"trouble_solver/tree"
)

func binaryTreePaths(root *tree.TreeNode) []string {
	if root == nil {
		return []string{}
	}

	var ans []string
	var findPath func(root *tree.TreeNode, path string)
	findPath = func(root *tree.TreeNode, path string) {
		if root == nil {
			return
		}

		str := fmt.Sprintf("%d", root.Val)
		path += str
		if root.Left == nil && root.Right == nil {
			ans = append(ans, path)
			return
		}

		findPath(root.Left, path+"->")
		findPath(root.Right, path+"->")
	}
	return ans
}

// 很明显，能够看出这是前序遍历，所以也能够用栈来迭代实现
func binaryTreePaths2(root *tree.TreeNode) []string {
	if root == nil {
		return []string{}
	}
	stackForNode, stackForPath := []*tree.TreeNode{}, []string{}
	var ans []string
	stackForNode = append(stackForNode, root)
	stackForPath = append(stackForPath, fmt.Sprintf("%d", root.Val))

	for len(stackForNode) > 0 {
		length, lenForStackPath := len(stackForNode), len(stackForPath)
		node := stackForNode[length-1]
		stackForNode = stackForNode[:length-1]

		if node.Left == nil && node.Right == nil {
			ans = append(ans, stackForPath[lenForStackPath-1])
			stackForPath = stackForPath[:lenForStackPath-1]
			continue
		}

		path := stackForPath[lenForStackPath-1]
		stackForPath = stackForPath[:lenForStackPath-1]
		if node.Left != nil {
			stackForNode = append(stackForNode, node.Left)
			stackForPath = append(stackForPath, path+"->"+fmt.Sprintf("%d", node.Left.Val))

		}
		if node.Right != nil {
			stackForNode = append(stackForNode, node.Right)
			stackForPath = append(stackForPath, path+"->"+fmt.Sprintf("%d", node.Right.Val))
		}
	}

	return ans

}
