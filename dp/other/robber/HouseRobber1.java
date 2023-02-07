/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 10:40:40
 * @LastEditTime: 2023-02-07 20:40:01
 */
package other.robber;

/**
 * leetcode 198 middle 打家劫舍1
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class HouseRobber1{
    public int rob(int[] nums) {
        int n = nums.length;
        // dp[i][0]表示的是偷第i间房的收益
        // dp[i][1]表示的是不偷第i间房的收益
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][1] + nums[i];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0]);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public int rob1DArray(int[] nums) {
        int n = nums.length;
        // dp[i]表示的是从第i间房开始偷的最大金额
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 2] + nums[i], dp[i + 1]);
        }

        return dp[0];
    }
}