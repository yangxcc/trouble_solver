/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-02 18:06:38
 * @LastEditTime: 2023-01-31 22:12:48
 */
package tree.bst;

import tree.TreeNode;

/**
 * leetcode 701 middle 二叉搜索树中的插入操作
 */
public class Insert {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        // 不写也可以
        // if (root.left == null && root.right == null && root.val < val) {
        //     root.right = new TreeNode(val);
        //     return root;
        // }

        // if (root.left == null && root.right == null && root.val > val) {
        //     root.left = new TreeNode(val);
        //     return root;
        // }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
