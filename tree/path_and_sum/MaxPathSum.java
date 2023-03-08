/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-23 09:59:00
 * @LastEditTime: 2023-03-03 16:14:11
 */
package tree.path_and_sum;

import tree.TreeNode;

/**
 * leetcode 124 hard 二叉树中的最大路径和
 * 
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接（不一定是父节点到子节点，也有可能是子节点出发到父节点），达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class MaxPathSum {
    int ans = Integer.MIN_VALUE;
    // 可以看到代码和leetcode 543 二叉树的直径很类似
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        pathSum(root);

        return ans;
    }

    // 最大路径和
    public int pathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftMaxPathSum = Math.max(0, pathSum(root.left));
        int rightMaxPathSum = Math.max(0, pathSum(root.right));

        ans = Math.max(ans, leftMaxPathSum+rightMaxPathSum+root.val);
        
        return Math.max(leftMaxPathSum, rightMaxPathSum) + root.val;
    }
}
