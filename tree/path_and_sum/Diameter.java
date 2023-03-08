/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-20 10:54:58
 * @LastEditTime: 2023-03-03 15:57:32
 */
package tree.path_and_sum;

import tree.TreeNode;

/**
 * leetcode 543 simple 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 */
public class Diameter {

    int res = -1;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 目的是以root为根的子树的高度，在求高度的同时能够计算出直径来
        height(root);
        return res;

        // 本来这里我是这么写的
        // int maxLeftHeight = height(root.left);
        // int maxRightHeight = height(root.right);
        // return maxLeftHeight + maxRightHeight;
        // 但是这样写 就有一个缺点，那就是默认了最大直径都是过根节点的，其实不然，比如
        /**
         *              1
         *           2      3
         *                4   5
         *             3       6
         *            4          7
         */
    }

    // 子树的最大高度
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        res = Math.max(res, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
