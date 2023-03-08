/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-02 10:41:55
 * @LastEditTime: 2023-01-31 22:24:48
 */
package tree.bst;

import tree.TreeNode;

/**
 * leetcode 98 middle 验证二叉搜索树
 */
public class Verify {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    public boolean isValidBSTHelper(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min.val) {
            return false;
        }

        if (max != null && root.val >= max.val) {
            return false;
        }

        return isValidBSTHelper(root.left, min, root) && isValidBSTHelper(root.right, root, max);
    }
}
