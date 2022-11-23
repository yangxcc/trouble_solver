package tree

/**
给定一个二叉树，我们在树的节点上安装摄像头。
节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
计算监控树的所有节点所需的最小摄像头数量。
*/

// 1. 因为每个摄像头都能够监视到自身以及其上下一层，所以为了保证摄像头最少，叶子节点上是不应该被安装摄像头的
// 2. 所以从后往前遍历，从后往前遍历我们可以使用后序遍历，（左右中），能够保证先访问下层再访问上层
// 3. 当遍历到某个节点时，我们需要判断是否要再该节点上加摄像头，这里我们给定节点的三个状态：未被覆盖（0），有摄像头（1），已经被覆盖了（2）
func minCameraCover(root *TreeNode) int {
	if root == nil {
		return 0
	}

	var ans int
	var checkNodeStatus func(root *TreeNode) int
	checkNodeStatus = func(node *TreeNode) int {
		if node == nil {
			return 2 // 空结点的状态需要设置为能够被覆盖到的，因为如果空节点没有被覆盖到，叶子节点也就需要加上摄像头了
		}
		left := checkNodeStatus(node.Left)
		right := checkNodeStatus(node.Right)

		// 如果左右节点中有一个没有被覆盖到（不能是&&，比如left装上了摄像头），那么就要让该节点加上一个摄像头
		if left == 0 || right == 0 {
			ans++
			return 1
		}
		// 如果左右节点有一个有摄像头，那么该节点是一定会被覆盖到的
		if left == 1 || right == 1 {
			return 2
		}
		// 如果左右节点都被覆盖到了，说明是该节点是没有覆盖的，因为是从下往上走的
		if left == 2 && right == 2 {
			return 0
		}

		return -1 // 不会走到这里
	}

	// 最后check一下root节点，如果root节点没有被覆盖，则给它加上一个摄像头
	if checkNodeStatus(root) == 0 {
		ans++
	}
	return ans
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}
