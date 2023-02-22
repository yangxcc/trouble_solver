/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-28 21:08:47
 * @LastEditTime: 2023-02-16 18:29:53
 */
package tree.position;

import java.util.ArrayDeque;
import java.util.Deque;

import tree.TreeNode;

/**
 * leetcode 513 middle 找树左下角的值
 * 
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 */
public class BottomLeftValue {
    // 层序遍历，最后一层第一次出现的元素
    // trick 只记录每一层的第一个就好了😂
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);
        int ans = root.val;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int flag = size;
            while (size > 0) {
                TreeNode node = queue.removeFirst();
                if (flag == size) {
                    ans = node.val;
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
                size--;
            }
        }

        return ans;
    }
}