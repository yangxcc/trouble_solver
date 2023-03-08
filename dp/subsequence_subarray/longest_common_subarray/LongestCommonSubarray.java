/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 19:28:29
 * @LastEditTime: 2023-02-22 19:08:48
 */
package subsequence_subarray.longest_common_subarray;

/**
 * leetcode 718 middle 最长重复子数组
 * 
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 */
public class LongestCommonSubarray{
    // int ans = 0;
    // public int findLength(int[] nums1, int[] nums2) {
    //     dp(nums1, nums2, 0, 0);
    //     return ans;
    // }

    // // dp函数的意思是nums1[i...]和nums2[j...]的最长公共子数组（以i，j结尾）
    // // [1,0,1,0,1]
    // // [0,1,1,1,1]
    // 这样完全变成了找最长公共子序列
    // public int dp(int[] nums1, int[] nums2, int i, int j) {
    //     if (i == nums1.length) {
    //         return 0;
    //     }
    //     if (j == nums2.length) {
    //         return 0;
    //     }
        
    //     int res = 0;
    //     if (nums1[i] == nums2[j]) {
    //         res = 1 + dp(nums1, nums2, i + 1, j + 1);
    //     } else {
    //         res = Math.max(dp(nums1, nums2, i + 1, j), dp(nums1, nums2, i, j + 1));
    //     }

    //     ans = Math.max(ans, res);

    //     return res;
    // }


    public int findLengthRightWay(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int ans = 0;
        
        int[][] dp = new int[m + 1][n + 1];
        // dp[0][...] = 0, dp[...][0] = 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                // } else {
                //     dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                // }

                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }
}