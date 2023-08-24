/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 19:48:37
 * @LastEditTime: 2023-02-07 15:44:38
 */
package dp.other.stock;

/**
 * leetcode 309 middle 最佳买卖股票时机（含冷冻期）
 * 
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *  卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *  注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class BestTimeTradeStock4 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;  // 0不持有， 1持有
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if (i - 2 == -1) {
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);    
                continue;
            }
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);    
        }

        return dp[n - 1][0];
    }
}
