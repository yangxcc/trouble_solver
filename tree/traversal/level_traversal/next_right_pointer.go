package level_traversal

import (
	"trouble_solver/tree"
)

// leetcode 116 填充每个节点的下一个右侧节点指针（完美二叉树）
// 这种方式不管是不是完美二叉树，都能够解决
func connect(root *tree.Node) *tree.Node {
	if root == nil {
		return nil
	}
	queue := []*tree.Node{}
	queue = append(queue, root)

	for len(queue) > 0 {
		size := len(queue)
		tmp := []*tree.Node{}
		for size > 0 {
			node := queue[0]
			queue = queue[1:]
			tmp = append(tmp, node)
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
			size--
		}
		for i := 0; i < len(tmp)-1; i++ {
			tmp[i].Next = tmp[i+1]
		}
	}
	return root
}

func connectRecusive(root *tree.Node) *tree.Node {
	if root == nil {
		return nil
	}

	var process func(node *tree.Node)
	process = func(node *tree.Node) {
		if node == nil {
			return
		}

		// 比如叶子节点
		if node.Left != nil {
			// 因为是完美二叉树，所以有left必定有right
			node.Left.Next = node.Right
			if node.Next != nil {
				node.Right.Next = node.Next.Left
			}
		}
		process(node.Left)
		process(node.Right)
	}
	process(root)
	return root
}

// leetcode 117 填充每个节点的下一个右侧节点指针（普通二叉树）
func connect2(root *tree.Node) *tree.Node {
	if root == nil {
		return nil
	}
	var process func(root *tree.Node)
	process = func(root *tree.Node) {
		if root == nil {
			return
		}
		if root.Left == nil && root.Right == nil {
			return
		}
		if root.Left != nil && root.Right != nil {
			root.Left.Next = root.Right
		}
		if root.Left != nil {
			root.Left.Next = getNextNode(root.Next)
		}
		if root.Right != nil {
			root.Right.Next = getNextNode(root.Next)
		}
		// 先遍历右树，在遍历左树，原因？？！！
		process(root.Right)
		process(root.Left)
	}
	process(root)

	return root
}

func getNextNode(node *tree.Node) *tree.Node {
	if node == nil {
		return nil
	}
	if node.Left != nil {
		return node.Left
	}
	if node.Left == nil && node.Right != nil {
		return node.Right
	}
	// 该节点没有子节点的情况，就去找next节点
	return getNextNode(node.Next)
}
