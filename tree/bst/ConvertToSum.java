/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-03 17:27:29
 * @LastEditTime: 2023-02-01 10:34:08
 */
package tree.bst;

import tree.TreeNode;

/**
 * leetcode 1038/538 middle 从二叉搜索树到更大和树
 * 
 * 给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 * 
*/ 
public class ConvertToSum {
    int curSum = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }

        process(root);
        return root;
    }

    // 对于一个节点，我们需要知道它右子树的和，可以使用中序遍历
    public void process(TreeNode root) {
        if (root == null) {
            return;
        }

        // 中序遍历是升序的，所以先遍历右子树，从大到小遍历
        process(root.right);
        curSum += root.val;
        root.val = curSum;
        process(root.left);
    }
}
