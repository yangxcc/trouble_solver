/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-01 12:07:32
 * @LastEditTime: 2023-03-08 22:34:17
 */
package array.slide_window;

/**
 * leetcode 209 simple 长度最小的子数组
 * 
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 */
public class MinimumSizeSubarraySum{
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sumWindow = 0, ans = Integer.MAX_VALUE;
        
        while (right < nums.length) {
            int num = nums[right];
            right++;

            sumWindow += num;

            while (sumWindow >= target) {
                ans = Math.min(ans, right-left);
                int l = nums[left];
                left++;
                sumWindow -= l;
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}