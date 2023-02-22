package greedy.wiggle_subsequence;

import java.util.Arrays;

/**
 * leetcode 376 middle 摆动序列
 * 
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。
 * 第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，
 * 第一个序列是因为它的前两个差值都是正数，
 * 第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * 
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        // dp[i][0]表示的是以i结尾，且nums[i]作为波谷的摆动序列的长度
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i][1] = Math.max(dp[j][0] + 1, dp[i][1]);
                } else if (nums[i] < nums[j]) {
                    dp[i][0] = Math.max(dp[j][1] + 1, dp[i][0]);
                }
            }

        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
