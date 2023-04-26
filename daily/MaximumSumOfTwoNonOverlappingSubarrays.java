/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-04-26 22:08:01
 * @LastEditTime: 2023-04-26 22:15:37
 */
package daily;

/**
 * leetcode 1031 middle 两个非重叠子数组的最大和
 * 
 * 没做出来，前缀和忘了，服气...
 * 
 * 给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，请你找出并返回两个非重叠 子数组 中元素的最大和，
 * 长度分别为 firstLen 和 secondLen 。
 * 
 * 长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。
 * 子数组是数组的一个 连续 部分。
 */
public class MaximumSumOfTwoNonOverlappingSubarrays {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = nums[0];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        /**
         * !!!
         * 前缀和求[left, right]区间的和的公式是preSum[right + 1] - preSum[left]
         */

        int ans = 0;

        /**
         * 这道题的另一个难点就是区间的判断，该用笔画的时候就画画，哎...
         */

        // 如果firstLen在前面
        for (int i = 0; i <= n - firstLen - secondLen; i++) {
            for (int j = i + firstLen; j <= n - secondLen; j++) {
                ans = Math.max(ans, preSum[i + firstLen] - preSum[i] + preSum[j + secondLen] - preSum[j]);
            }
        }

        // 如果secondLen在前面
        for (int i = 0; i <= n - firstLen - secondLen; i++) {
            for (int j = i + secondLen; j <= n - firstLen; j++) {
                ans = Math.max(ans, preSum[i + secondLen] - preSum[i] + preSum[j + firstLen] - preSum[j]);
            }
        }

        return ans;
    }
}
