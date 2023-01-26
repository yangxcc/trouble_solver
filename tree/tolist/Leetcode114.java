/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-26 08:58:22
 * @LastEditTime: 2023-01-26 09:07:35
 */
package tree.tolist;

import tree.TreeNode;

/**
 * leetcode 114 middle 二叉树展开为链表
 * 
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 
 * 整体思路看着图很容易理解
 */
public class Leetcode114 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        TreeNode cur = root;
        TreeNode rightTmp = cur.right;
        cur.right = cur.left;
        // 不要忘了把这里给置为空
        cur.left = null;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = rightTmp;
    }
}
