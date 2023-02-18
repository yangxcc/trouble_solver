/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-17 17:05:50
 * @LastEditTime: 2023-02-18 15:51:28
 */
package skill.jump_game;

/**
 * leetcode 45 middle 跳跃游戏2
 * 
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。
 * 换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i] 
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        int n = nums.length;
        // dp[i]表示的是到达i位置处的最小跳跃次数
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = i; // 一步步的走，最多也只需要i步
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}
