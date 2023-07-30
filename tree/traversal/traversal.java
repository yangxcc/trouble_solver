/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-19 15:19:25
 * @LastEditTime: 2023-01-19 21:14:51
 */
package tree.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import tree.TreeNode;

public class Traversal {
    // 递归法的前序 中序 后序 略...
    public List<Integer> preOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 遍历顺序：根左右
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

            ans.add(node.val);
        }

        return ans;
    }

    /**
     * 中序遍历的核心思想是：对于当前节点，优先遍历其左子树
     * 左根右
     */
    public List<Integer> inOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                ans.add(node.val);
                cur = node.right;
            }
        }
        return ans;
    }

    /**
     * 根左右 -> 根右左 -> 左右根
     * 能够有先序遍历变成后续遍历
     * 
     */
    public List<Integer> postOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            ans.add(node.val);
            
            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        Collections.reverse(ans);

        return ans;
    }
}