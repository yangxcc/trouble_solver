/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-01 11:17:38
 * @LastEditTime: 2023-02-06 21:21:43
 */
package subsequence_subarray.longest_increasing_subsequence;

import java.util.Arrays;

/**
 * leetcode 300 middle 最长递增子序列
 * 
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]表示的是[0, i]区间内且以i节点结尾的最长严格递增子序列
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }

        return ans;
    }
}
