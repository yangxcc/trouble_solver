package symmetry

import "trouble_solver/tree"

// 判断一棵树是否为对称二叉树
func isSymmetric(root *tree.TreeNode) bool {
	if root == nil {
		return true
	}

	var process func(t1 *tree.TreeNode, t2 *tree.TreeNode) bool
	process = func(t1, t2 *tree.TreeNode) bool {
		if t1 == nil && t2 == nil {
			return true
		}
		if t1 != nil && t2 != nil && t1.Val == t2.Val {
			return process(t1.Left, t2.Right) && process(t1.Right, t2.Left)
		}
		return false
	}

	return process(root.Left, root.Right)
}

// 同样能够使用迭代完成，其实本质上也是层序遍历，只不过与经典写法有点不同，这个得两两入队
// 其实从下面的代码中也能够看出，这里不仅仅能够使用队列，使用栈也一样，重点是保证进入的顺序
func isSymmetric2(root *tree.TreeNode) bool {
	if root == nil {
		return true
	}
	queue := []*tree.TreeNode{}
	queue = append(queue, root.Left)
	queue = append(queue, root.Right)
	for len(queue) > 0 {
		node1 := queue[0]
		node2 := queue[1]
		queue = queue[2:]
		// 一定要有这个判断
		if node1 == nil && node2 == nil {
			continue
		}
		if node1 != nil && node2 != nil && node1.Val == node2.Val {
			// 需要按照顺序加入队列中，因为每次从队列中取出都是一个pair
			queue = append(queue, node1.Left)
			queue = append(queue, node2.Right)
			queue = append(queue, node1.Right)
			queue = append(queue, node2.Left)
		} else {
			return false
		}
	}
	return true
}
