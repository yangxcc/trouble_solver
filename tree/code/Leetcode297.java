/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-27 09:53:59
 * @LastEditTime: 2023-01-31 12:09:45
 */
package tree.code;

import java.util.ArrayDeque;
import java.util.Deque;

import tree.TreeNode;

/**
 * leetcode 297 hard 二叉树的序列化与反序列化
 */
public class Leetcode297 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }

        sb.append(root.val + ",");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("#,")) {
            return null;
        }

        String[] elements = data.split(",");
        Deque<String> q = new ArrayDeque<>();
        for (String ele : elements) {
            q.add(ele);
        }

        return deserializeHelper(q);
    }

    public TreeNode deserializeHelper(Deque<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String nodeVal = queue.poll();
        if (nodeVal.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodeVal));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        
        return root;
    }
}
