/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 16:06:13
 * @LastEditTime: 2023-02-07 21:23:41
 */
package other.robber;

import tree.TreeNode;

/**
 * leetcode 337 middle 打家劫舍问题3
 * 
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
 * 聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 */
public class HouseRobber3 {
    public int rob(TreeNode root) {
        return dp(root);
    }

    // 从root节点开始的最大收益
    public int dp(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        // 对于某个节点，偷或者不偷
        int rob = root.val +
                (root.left == null ? 0 : dp(root.left.left) + dp(root.left.right)) + 
                (root.right == null ? 0 : dp(root.right.left) + dp(root.right.right));

        int notRob = dp(root.left) + dp(root.right);

        return Math.max(rob, notRob);
    }

    public int robBetter(TreeNode root) {
        int[] ans = dpBetter(root);
        return Math.max(ans[0], ans[1]);
    }

    /**
     * 
     * @param root
     * @return ans[0] 表示从偷的最大收益， ans表示不偷的最大收益
     */
    public int[] dpBetter(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] leftState = dpBetter(root.left);
        int[] rightState = dpBetter(root.right);

        int rob = root.val + leftState[1] + rightState[1];
        // bugfix
        // int notRob = leftState[0] + rightState[0];
        int notRob = Math.max(leftState[0], leftState[1]) + Math.max(rightState[0], rightState[1]);

        return new int[]{rob, notRob};
    }
}
