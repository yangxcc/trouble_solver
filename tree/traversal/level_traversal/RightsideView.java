package tree.traversal.level_traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tree.TreeNode;

/**
 * leetcode 199 middle 二叉树的右视图
 * 
 * 该题没什么难度，就是找到层序遍历的最后一个节点
 */
public class RightsideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }

                if (i == size - 1) {
                    ans.add(node.val);
                }
            }
        }

        return ans;
    }
}
