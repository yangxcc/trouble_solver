/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-07 16:33:33
 * @LastEditTime: 2023-02-07 16:45:55
 */
package other.stock;

/**
 * 将股票问题中的所有条件融合在一起，写一个代码框架
 * 存在交易次数限制，存在冷冻期，存在交易费
 */
public class AllConditions {
    /**
     * 
     * @param maxTradeCount 最大交易次数限制
     * @param prices
     * @param cooldown 冷冻期，卖出股票后，你无法在第冷冻期内买入股票 
     * @param fee 交易费
     * @return
     */
    public int maxProfit_all_in_one(int maxTradeCount, int[] prices, int cooldown, int fee) {
        int n = prices.length;
        // dp[i][j][0] 表示在第i天第j次交易不持有股票的最大收益
        int[][][] dp = new int[n][maxTradeCount + 1][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE; // 没交易却持有了股票，不合法
        }

        for (int i = 0; i < n; i++) {
            for (int k = 1; k <= maxTradeCount; k++) {
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i] - fee;
                    continue;
                }

                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                if (i - cooldown == -1) {
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], -prices[i] - fee);
                    continue;
                }
                
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - cooldown][k - 1][0] - prices[i] - fee);
            }
        }
        return dp[n - 1][maxTradeCount][0];
    }
}
