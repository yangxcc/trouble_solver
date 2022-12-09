/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 16:14:22
 * @LastEditTime: 2022-12-09 17:11:12
 */
package robber

/**
问题描述：
小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。

示例：
输入: root = [3,4,5,1,3,null,1]
输出: 9
解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
从这个实例中可以看出，抢劫的房间可以处于同一层，只要是不相邻就好
*/
// 与问题1，2不同的是，该问题变成了在二叉树排列的房屋上进行偷盗
// 使用递归但是超时了...
func rob3(root *TreeNode) int {
	memo := map[*TreeNode]int{}
	var process func(root *TreeNode) int
	process = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if v, ok := memo[root]; ok {
			return v
		}
		// 这个使用dp数组不太好定位了，使用递归吧
		// 如果是抢了当前位置，那么下一次抢的就是node.left.left, node.left.right, node.right.left, node.right.right
		robVal := root.Val
		if root.Left != nil {
			robVal += (rob3(root.Left.Left) + rob3(root.Left.Right))
		}
		if root.Right != nil {
			robVal += (rob3(root.Right.Left) + rob3(root.Right.Right))
		}

		// 如果不抢当前节点，就去看看左右子树
		notRobVal := rob3(root.Left) + rob3(root.Right)
		res := max(robVal, notRobVal)
		memo[root] = res
		return res
	}

	return process(root)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func rob3Better(root *TreeNode) int {
	// int[0]表示的是抢root这一点带来的最大收益，int[1]表示的是不抢root这一点带来的最大收益
	var process func(root *TreeNode) []int
	process = func(root *TreeNode) []int {
		if root == nil {
			return []int{0, 0}
		}

		left := process(root.Left)
		right := process(root.Right)

		rob := root.Val + left[1] + right[1]
		// 不抢root，那么对于他的左右子树，我们可以抢也可以不抢
		notRob := max(left[0], left[1]) + max(right[0], right[1])

		return []int{rob, notRob}
	}

	ans := process(root)
	return max(ans[0], ans[1])
}
