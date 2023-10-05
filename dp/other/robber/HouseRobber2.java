/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 10:56:38
 * @LastEditTime: 2023-02-07 21:07:49
 */
package dp.other.robber;

/**
 * leetcode 213 middle 打家劫舍2
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 */
public class HouseRobber2 {
    // 头尾相连(环形)
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
    }

    /**
     * 因为头尾相连的话，只有三种情况，头尾都不偷，头偷尾不偷，头不偷尾偷，第一种情况肯定是最少的，直接不考虑
     * 
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int robHelper(int[] nums, int start, int end) {
        int n = nums.length;
        // dp[i]表示的是从第i间房开始偷的最大收益
        int[] dp = new int[n + 2];

        for (int i = end; i >= start; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }

        return dp[start];
    }
}
