/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-24 20:49:16
 * @LastEditTime: 2023-03-03 16:24:04
 */
package tree.reverse;

import tree.TreeNode;

/**
 * leetcode 226 simple 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }
        
        TreeNode leftTmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(leftTmp);

        return root;
    }
}
