/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-02 14:19:16
 * @LastEditTime: 2023-02-01 11:26:04
 */
package tree.bst;

import tree.TreeNode;

/**
 * leetcode 530 simple BST中的最小绝对差
 * 
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 */
public class MinAbsoluteDifference {
    int ans = Integer.MAX_VALUE;
    
    // 不一定最小值就是当前节点减去左子树/右子树，比如[236,104,701,null,227,null,911]
    public int getMinimumDifferenceWrong(TreeNode root) {
        if (root == null) {
            return -1;
        }

        if (root.left != null) {
            ans = Math.min(ans, root.val - root.left.val);
        }
        if (root.right != null) {
            ans = Math.min(ans, root.right.val - root.val);
        }
        getMinimumDifferenceWrong(root.left);
        getMinimumDifferenceWrong(root.right);

        return ans;
    }

    TreeNode pre = null;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return ans;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);

        if (pre != null && root.val - pre.val < ans) {
            ans = root.val - pre.val;
        }
        pre = root;

        inOrder(root.right);
    }
}
