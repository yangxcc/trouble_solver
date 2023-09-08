/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-06 17:59:49
 * @LastEditTime: 2023-02-22 14:10:43
 */
package dp.bag_problem.complete_package.coin_change2;

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
        // base case
        dp[0] = 1;

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

    // 二维dp,可以作为从数组中能够构成target的组合数的模板，需要注意是能够无限选还是只能够选一次
    public int coinChange2D(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        // 当target==0时，所有的组合数都为1
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        // dp[0][0] = 0,没硬币自然就没办法组合
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 无限个就是dp[i]，而不是dp[i - 1]
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[n][amount];
    }
}
