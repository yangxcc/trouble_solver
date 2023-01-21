/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-01 19:55:33
 * @LastEditTime: 2023-01-21 14:44:25
 */
package tree.path_and_sum;

import tree.TreeNode;

/**
 * leetcode 112 simple 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
 * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && root.val - targetSum == 0) {
            return true;
        }

        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val);
    }
}
