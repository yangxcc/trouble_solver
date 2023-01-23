/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-27 18:03:13
 * @LastEditTime: 2023-01-23 09:47:30
 */
package tree.path_and_sum;

import tree.TreeNode;

/**
 * leetcode 404 simple 左叶子之和
 * 
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 */
public class SumOfLeftLeaves {
    int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val;
        }

        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);

        return res;
    }
}
