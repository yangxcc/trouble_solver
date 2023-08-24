/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-08 20:05:59
 * @LastEditTime: 2023-02-07 15:51:16
 */
package dp.other.stock;

/**
 * leetcode 714 middle 买卖股票的最佳时机含手续费
 * 
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 */
public class BestTimeTradeStock5 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // dp[i]表示的是 第i天 持有(0)/不持有(1)的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0] - fee; 

        for (int i = 1; i < n; i++) {
            // 可以进行多次交易
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }

        return dp[n - 1][1];
    }
}
