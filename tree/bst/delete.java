package tree.bst;

import tree.TreeNode;

/**
 * leetcode 450 middle 删除二叉搜索树中的节点
 */
public class Delete {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // 使用右子树的最左节点来代替root
        if (root.val == key) {
            if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            } else {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }

                cur.right = deleteNode(root.right, cur.val);
                cur.left = root.left;
                return cur;
            }
        }

        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }

        return root;
    }
}
