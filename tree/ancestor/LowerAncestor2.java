package tree.ancestor;

import tree.TreeNode;

/**
 * leetcode 1644 middle 二叉树的最近公共祖先2
 * 如果节点p和节点q不在二叉树中，那么就返回null
 * 和普通的二叉树的最近公共祖先不同的是，题目中规定了p和q一定存在
 */
public class LowerAncestor2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = LCA(root, p, q);
        if (hasP && hasQ) {
            return ans;
        }

        return null;
    }

    boolean hasP = false;
    boolean hasQ = false;
    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p) {
            hasP = true;
            return root;
        }
        if (root == q) {
            hasQ = true;
            return root;
        }

        TreeNode findInLeft = LCA(root.left, p, q);
        TreeNode findInRight = LCA(root.right, p, q);

        if (findInLeft == null && findInRight == null) {
            return null;
        }
        if (findInLeft == null) {
            return findInRight;
        }
        if (findInRight == null) {
            return findInLeft;
        }

        return root;
    }
}
