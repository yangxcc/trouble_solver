package traversal

import (
	"fmt"
	"trouble_solver/tree"
)

/*
	二叉树的深度优先搜索使用栈，宽度优先搜索使用队列
	递归实现的本质是将每一次调用中用到的局部变量、参数值和返回地址等都压入到栈中，当函数返回时，从栈顶弹出上一次递归的各项参数
*/

// 前序遍历：递归法
func preOrder(root *tree.TreeNode) {
	if root == nil {
		return
	}
	// 进行操作
	fmt.Println(root.Val)
	preOrder(root.Left)
	preOrder(root.Right)
}

// 前序遍历：迭代法
func preOrderWithLoop(root *tree.TreeNode) {
	if root == nil {
		return
	}
	stack := tree.Stack{}
	stack.Push(root)

	for !stack.Empty() {
		// 进行操作
		node := stack.Pop()
		fmt.Println(node.Val)

		// 这里唯一需要注意的是：由于栈先进后出的性质，所以我们要先把右树加进去，再加左树
		if root.Right != nil {
			stack.Push(node.Right)
		}
		if root.Left != nil {
			stack.Push(node.Left)
		}
	}
}

// 中序遍历：递归法
func inOrder(root *tree.TreeNode) {
	if root == nil {
		return
	}

	inOrder(root.Left)
	fmt.Println(root.Val)
	inOrder(root.Right)
}

// 中序遍历：迭代法，对于当前节点，优先遍历左子树
func inOrderWithLoop(root *tree.TreeNode) {
	if root == nil {
		return
	}
	stack, cur := tree.Stack{}, root

	for cur != nil || !stack.Empty() {
		if cur != nil {
			stack.Push(cur)
			cur = cur.Left
		} else {
			node := stack.Pop()
			cur = &node
			fmt.Println(cur.Val)
			cur = cur.Right
		}
	}

}

func postOrder(root *tree.TreeNode) {
	if root == nil {
		return
	}

	postOrder(root.Left)
	postOrder(root.Right)
	fmt.Println(root.Val)
}

// 后序遍历：迭代法，根左右 -> 根右左 -> 左右根
// 所以我们可以调整一下前序遍历的代码顺序，变成根右左，然后反转结果集就是后序遍历
func PostOrderWithLoop(root *tree.TreeNode) {
	if root == nil {
		return
	}

	var ans []int
	stack := tree.Stack{}
	stack.Push(root)
	for !stack.Empty() {
		node := stack.Pop()
		ans = append(ans, node.Val)
		if node.Left != nil {
			stack.Push(node.Left)
		}
		if node.Right != nil {
			stack.Push(node.Right)
		}
	}

	// reverse ans
	i, j := 0, len(ans)-1
	for i < j {
		ans[i], ans[j] = ans[j], ans[i]
		i++
		j--
	}

	for _, x := range ans {
		fmt.Println(x)
	}
}
