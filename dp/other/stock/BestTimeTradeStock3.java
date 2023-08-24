/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 17:46:52
 * @LastEditTime: 2023-02-07 15:37:38
 */
package dp.other.stock;

/**
 * leetcode 123 hard 买卖股票的最佳时机3
 * 
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 K笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class BestTimeTradeStock3 {
    public int maxProfit(int[] prices, int k) {
        int n = prices.length;
        int tradeNums = k;
        // dp[i][0][1]表示的是第i天第0次交易持有股票的最大利润
        int[][][] dp = new int[n][tradeNums + 1][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE; // 没发生交易，但持有了股票，不合题意
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= tradeNums; j++) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }

        return dp[n - 1][tradeNums][0];
    }
}
