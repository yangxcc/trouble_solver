/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-02 15:33:41
 * @LastEditTime: 2023-02-16 20:05:37
 */
package tree.ancestor;

import tree.TreeNode;

/**
 * leetcode 剑指 Offer 68 - II. 二叉树的最近公共祖先
 */
public class LowerAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // 在左子树中找p点和q点
        TreeNode findInLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode findInRight = lowestCommonAncestor(root.right, p, q);

        if (findInLeft != null && findInRight != null) {
            return root;
        }

        return findInLeft == null ? findInRight : findInLeft;
    }
}
