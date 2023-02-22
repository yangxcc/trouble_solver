/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-25 20:13:30
 * @LastEditTime: 2023-02-16 17:52:57
 */
package tree.balance;

import tree.TreeNode;

/**
 * leetcode 110 simple 平衡二叉树
 * 
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class Balance {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) >= 0;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight >= 0 && rightHeight >= 0 && Math.abs(leftHeight - rightHeight) <= 1) {
            return Math.max(leftHeight, rightHeight) + 1;
        }
        return -1;
    }
}
