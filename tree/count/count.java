package tree.count;

import tree.TreeNode;

/**
 * leetcode 222 middle 完全二叉树节点的个数
 * 
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，
 * 其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 */
public class Count {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode leftPointer = root.left;
        TreeNode rightPointer = root.right;
        int leftHeight = 0, rightHeight = 0;
        while (leftPointer != null) {
            leftPointer = leftPointer.left;
            leftHeight++;
        }
        while (rightPointer != null) {
            rightPointer = rightPointer.right;
            rightHeight++;
        }

        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, rightHeight) - 1;
        }

        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        return leftCount + rightCount + 1;
    }
}