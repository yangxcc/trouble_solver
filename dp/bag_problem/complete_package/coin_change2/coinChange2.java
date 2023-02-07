/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-06 17:59:49
 * @LastEditTime: 2023-02-07 14:09:04
 */
package bag_problem.complete_package.coin_change2;

/**
 * leetcode 518 middle 零钱兑换2
 * 
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。 
 * 题目数据保证结果符合 32 位带符号整数。
 * 
 */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        // dp[i]表示的是凑成i的组合数
        int[] dp = new int[amount + 1];

        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i < coin) {
                    continue;
                }
                // 只有两种选择，选这个硬币或者不选这个硬币，将这两种情况加起来
                dp[i] += dp[i - coin];
                // 不是dp[i] += dp[i - coin] * dp[coin]; 选择这个硬币,dp[coin]=1，就只有这一种选择
            }
        }

        return dp[amount];
    }
}
