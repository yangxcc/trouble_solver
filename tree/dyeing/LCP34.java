/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-24 21:20:42
 * @LastEditTime: 2023-05-24 21:28:56
 */
package tree.dyeing;

import tree.TreeNode;

/**
 * leetcode LCP 34. 二叉树染色 middle
 * 小扣有一个根结点为 root 的二叉树模型，初始所有结点均为白色，可以用蓝色染料给模型结点染色，
 * 模型的每个结点有一个 val 价值。小扣出于美观考虑，希望最后二叉树上每个蓝色相连部分的结点个数不能超过 k 个，
 * 求所有染成蓝色的结点价值总和最大是多少？
 */
public class LCP34 {
    public int maxValue(TreeNode root, int k) {
        int[] dp = dfs(root, k);
        int ans = 0;
        for (int d : dp) {
            ans = Math.max(ans, d);
        }

        return ans;
    }

    /**
     * 返回一个数组dp，其中dp[i]表示的是以cur为根节点的子树，其中蓝色相连节点个数为i的最大价值
     * 
     * 这是不是就叫做树形dp？？
     */
    private int[] dfs(TreeNode cur, int k) {
        int[] dp = new int[k];
        if (cur == null) {
            return dp;
        }

        int[] left = dfs(cur.left, k);
        int[] right = dfs(cur.right, k);

        // dp[0]表示的是0个相连的蓝色节点，也就是说当前节点是白色的
        int maxInLeft = 0, maxInRight = 0;
        for (int i = 0; i < k; i++) {
            maxInLeft = Math.max(maxInLeft, left[i]);
            maxInRight = Math.max(maxInRight, right[i]);
        }

        dp[0] = maxInLeft + maxInRight;

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j - 1] + cur.val);
            }
        }

        return dp;
    }
}
