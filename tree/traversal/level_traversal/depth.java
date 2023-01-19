/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-24 20:09:00
 * @LastEditTime: 2023-01-19 14:04:42
 */
package tree.traversal.level_traversal;

import tree.TreeNode;

public class Depth {
    // 递归写法
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }
    // 非递归写法就是层序遍历

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        return leftDepth > rightDepth ? rightDepth + 1 : leftDepth + 1;
    }
}