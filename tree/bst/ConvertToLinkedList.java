/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-16 11:34:03
 * @LastEditTime: 2023-02-16 12:43:48
 */
package tree.bst;

import tree.TreeNode;

/**
 * leetcode 剑指offer-36 middle 二叉搜索树与双向链表
 * 
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 
 */
public class ConvertToLinkedList {
    TreeNode pre, head;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        inOrder(root);
        pre.right = head;
        head.left = pre;

        return head;
    }

    public void inOrder(TreeNode cur) {
        if (cur == null) {
            return;
        }

        inOrder(cur.left);

        if (pre == null) {
            // 说明cur已经到了最左侧的节点，也就是链表的head
            head = cur;
        } else {
            pre.right = cur;
        }

        cur.left = pre;
        pre = cur;


        inOrder(cur.right);
    }
}
