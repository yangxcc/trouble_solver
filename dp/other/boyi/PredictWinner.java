/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-07 21:32:15
 * @LastEditTime: 2023-03-09 15:42:40
 */
package dp.other.boyi;

import java.util.Scanner;

/**
 * leetcode 486 middle 预测赢家
 * 
 * 给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
 * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。
 * 每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），
 * 取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
 * 
 * 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。
 * 你可以假设每个玩家的玩法都会使他的分数最大化。
 */
public class PredictWinner {
    public boolean PredictTheWinner(int[] nums) {
        return first(nums, 0, nums.length - 1) >= second(nums, 0, nums.length - 1);
    }

    // 从start到end区间内玩家的收益
    public int first(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return nums[start];
        }

        return Math.max(nums[start] + second(nums, start + 1, end), nums[end] + second(nums, start, end - 1));
    }

    public int second(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }

        // 在第二人选的区间上，第二人肯定选最有利自己的，所以是对第一人最不利的，因此上面的base case也是返回0
        // 即第二人把唯一的一个数选了，第一人再选的时候啥也不剩了
        return Math.min(first(nums, start + 1, end), first(nums, start, end - 1));
    }

    public boolean PredictTheWinnerDpArray(int[] nums) {
        int n = nums.length;
        // dp[i][j][0]表示的是先手在[i,j]区间内的最大获利, dp[i][j][1]表示的是后手在[i, j]区间内的最大获利
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = nums[i];
            dp[i][i][1] = 0;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 这里的状态转移方程不能这么写，因为如果像下面这么写，表示的是在区间[i,j]上先选对后选是没有影响的
                // 这样是没办法区分出先后手的，先选会影响到后选
                // dp[i][j][0] = Math.max(nums[i] + dp[i + 1][j][1], nums[j] + dp[i][j - 1][1]);
                // dp[i][j][1] = Math.max(dp[i + 1][j][0], dp[i][j - 1][0]);
                if (nums[i] + dp[i + 1][j][1] > nums[j] + dp[i][j - 1][1]) {
                    dp[i][j][0] = nums[i] + dp[i + 1][j][1];
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = nums[j] + dp[i][j - 1][1];
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }

        return dp[0][n - 1][0] >= dp[0][n - 1][1];
    }
}