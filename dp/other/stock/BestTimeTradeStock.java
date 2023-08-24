/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 17:11:07
 * @LastEditTime: 2023-02-07 14:48:21
 */
package dp.other.stock;

/**
 * leetcode 121 simple 买卖股票的最佳时机
 * 
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class BestTimeTradeStock {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i]表示的是 第i天 持有(0)/不持有(1)的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0]; 

        for (int i = 1; i < n; i++) {
            // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 只能选择一天进行交易，所以不能是上面这种状态方程
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }

        return dp[n - 1][1];
    }
}