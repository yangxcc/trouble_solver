/*
 * @Author: yangxcc
 * @version: Search
 * @Date: 2022-11-02 10:37:47
 * @LastEditTime: 2023-01-31 22:06:05
 */
package tree.bst;

import tree.TreeNode;

/**
 * leetcode 700 simple 二叉搜索树中的搜索
 */
public class Search {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}