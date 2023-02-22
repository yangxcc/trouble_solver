package tree.ancestor;

import tree.TreeNode;

/**
 * leetcode 剑指 Offer 68-I simple 二叉搜索树的最近公共祖先
 */
public class BSTLowerAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        if ((root.val - p.val) * (root.val - q.val) < 0) {
            return root;
        } else if (root.val - p.val > 0) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val - p.val < 0) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return null;
    }
}
