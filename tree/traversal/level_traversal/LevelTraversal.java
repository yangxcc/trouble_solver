/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-23 16:26:45
 * @LastEditTime: 2023-01-19 14:16:39
 */
package tree.traversal.level_traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tree.TreeNode;

/**
 * leetcode 102 middle 二叉树的层序遍历
 */
public class LevelTraversal {
    public List<List<Integer>> levelTraverse(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                level.add(node.val);
                size--;
            }
            ans.add(new ArrayList<>(level));
        }

        return ans;
    }
}