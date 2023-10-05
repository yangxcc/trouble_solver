package dp.subsequence_subarray.maximum_subarray;

import java.util.Arrays;

/**
 * leetcode 152 middle 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * 
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 */
public class MaxSubarrayProduct {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];
        for (int i = 0; i < n; i++) {
            dpMax[i] = nums[i];
            dpMin[i] = nums[i];
        }
        // int ans = Integer.MIN_VALUE;
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMax[i], Math.max(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
            dpMin[i] = Math.min(dpMin[i], Math.min(nums[i] * dpMin[i - 1], nums[i] * dpMax[i - 1]));
            ans = Math.max(ans, dpMax[i]);
        }

        return ans;
    }
}
