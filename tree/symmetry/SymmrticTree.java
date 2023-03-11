package tree.symmetry;

import tree.TreeNode;

/**
 * leetcode 剑指offer28/101 simple 对称二叉树
 * 
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 */
public class SymmrticTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        } else {
            return helper(t1.left, t2.right) && helper(t1.right, t2.left);
        }
    }
}