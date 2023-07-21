/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-11 10:11:21
 * @LastEditTime: 2023-04-16 10:53:05
 */
package array.slide_window;

import java.util.HashMap;

/**
 * leetcode 2461. 长度为 K 子数组中的最大和
 * 给你一个整数数组 nums 和一个整数 k 。请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：
 * 子数组的长度是 k，且子数组中的所有元素 各不相同 。
 * 返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 0 。
 * 子数组 是数组中一段连续非空的元素序列。
 * 
 */
public class MaximumSumSubarray {
    public long maximumSubarraySum(int[] nums, int k) {
        int left = 0, right = 0;
        long sumWindow = 0, ans = 0;
        HashMap<Integer, Integer> window = new HashMap<>();

        while (right < nums.length) {
            int num = nums[right];
            right++;

            window.put(num, window.getOrDefault(num, 0) + 1);
            sumWindow += num;

            if (right - left == k) {
                if (window.size() == k) {
                    ans = Math.max(ans, sumWindow);
                }
            } else if (right - left > k) {
                int l = nums[left];
                left++;
                sumWindow -= l;

                window.put(l, window.get(l) - 1);
                if (window.get(l) == 0) {
                    window.remove(l);
                }

                if (window.size() == k) {
                    ans = Math.max(ans, sumWindow);
                }
            }
        }

        return ans;
    }
}