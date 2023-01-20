/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-20 10:54:58
 * @LastEditTime: 2023-01-20 11:04:19
 */
package tree.path_and_sum;

import tree.TreeNode;

/**
 * leetcode 543 simple 二叉树的直径
 */
public class Diameter {

    int res = -1;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

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
