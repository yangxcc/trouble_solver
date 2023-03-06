/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-01 14:35:03
 * @LastEditTime: 2023-03-06 09:37:33
 */
package subsequence_subarray.maximum_subarray;

/**
 * leetcode 53 middle 最大子数组和
 * 
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 */
public class MaxSubarray {
    // 使用前缀和技巧
    public int maxSubArrayWithPrefixSum(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] > ans) {
                    ans = preSum[i] - preSum[j];
                }
            }
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // ans的初始值没有一下子写对，最开始写的是Integer.MIN_VALUE，这样写对于只有一个元素的数组得不到正确答案，对于[-1, -2]这样的case也得不到答案
        int ans = nums[0];
        // dp[i]表示的是以当前元素结尾的子数组的和
        int[] dp = new int[n];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
        }

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], dp[i]);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}


class daya0306 {
    public int maxSubArray(int[] arr) {
        int n = arr.length;
        if (n <= 0) {
            return Integer.MIN_VALUE;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }
        
        // 最开始ans的初始值还是没写对
        int ans = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], dp[i]);
            ans = Math.max(dp[i], ans);
        }

        return ans;
    }
}