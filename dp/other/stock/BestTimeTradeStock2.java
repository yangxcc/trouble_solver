/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 17:36:11
 * @LastEditTime: 2023-02-07 14:49:57
 */
package other.stock;

/**
 * leetcode 122 middle 买卖股票的最佳时机2
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 */
public class BestTimeTradeStock2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i]表示的是 第i天 持有(0)/不持有(1)的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0]; 

        for (int i = 1; i < n; i++) {
            // 可以进行多次交易
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }

        return dp[n - 1][1];
    }
}
