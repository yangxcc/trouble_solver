package tree.tolist;

import tree.TreeNode;

/**
 * 剑指offer36 二叉搜索树与双向链表 middle https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 */
public class BSTToList {
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        // 连接起头尾
        head.left = pre;
        pre.right = head;

        return head;
    }

    TreeNode pre = null;
    TreeNode head = null; // 记录头节点的位置
    private void inOrder(TreeNode cur) {
        if (cur == null) {
            return;
        }

        inOrder(cur.left);
        if (pre == null) {
            // 说明走到最左边了，记录下这个最左边的值（最小的值）
            head = cur;
        } else {
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;

        inOrder(cur.right);
    }
}
