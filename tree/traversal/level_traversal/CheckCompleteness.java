package tree.traversal.level_traversal;

import tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 958 middle 二叉树的完全性检验
 * 给定一个二叉树的 root ，确定它是否是一个 完全二叉树 。
 */
public class CheckCompleteness {
    // 使用层序遍历，对于完全二叉树，如果是完全二叉树，那么遍历到最后，队列中剩下的应该全部都是null指针
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        // ArrayDeque中不能够有null
        // Deque<TreeNode> q = new ArrayDeque<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean end = false; // 遍历完所有的非空节点之后变为true
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode node = q.poll();
                if (node == null) {
                    // 理论上应该遍历完了所有的非空节点
                    end = true;
                } else {
                    if (end) {
                        return false;
                    }
                    q.offer(node.left);
                    q.add(node.right);
                }

                size--;
            }
        }

        return true;
    }
}

/**
 * 统计完全二叉树中节点的个数
 */
class CountCompletenessTree {
    public int count(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = 0, rightDepth = 0;
        TreeNode leftIdx = root, rightIdx = root;
        while (leftIdx != null) {
            leftDepth++;
            leftIdx = leftIdx.left;
        }
        while (rightIdx != null) {
            rightDepth++;
            rightIdx = rightIdx.right;
        }

        if (leftDepth == rightDepth) {
            return (1 << leftDepth) - 1;
        }

        return count(root.left) + count(root.right) + 1;
    }
}
