/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-06 16:20:11
 * @LastEditTime: 2023-05-22 20:04:12
 */
package array.binary_search.application;

/**
 * leetcode 410 hard 分割数组的最大值
 * 
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组。
 * 设计一个算法使得这 k 个子数组各自和的最大值最小。
 * 
 * 实际上和leetcode 1011一摸一样，不同的是这个更难理解了
 */
public class Leetcode410 {
    public int splitArray(int[] nums, int k) {
        // 子数组和的最大值的范围是[minSum, maxSum]
        int minSum = 0, maxSum = 0;
        for (int num : nums) {
            minSum = Math.max(minSum, num);
            maxSum += num;
        }

        while (minSum <= maxSum) {
            int mid = minSum + (maxSum - minSum) / 2;
            if (findK(nums, mid) == k) {
                maxSum = mid - 1;
            } else if (findK(nums, mid) < k) {
                // 想让findK大一些，也就是让mid小一些，因为子数组和的最大值越小，能分的组就越多
                maxSum = mid - 1;
            } else if (findK(nums, mid) > k) {
                minSum = mid + 1;
            }
        }

        return minSum;
    }

    // 令连续的子数组和尽可能地接近sum，以sum来切分能分出几组
    public int findK(int[] nums, int sum) {
        int k = 0;
        for (int i = 0; i < nums.length; ) {
            int tmpSum = sum;
            while (i < nums.length) {
                tmpSum -= nums[i];
                if (tmpSum < 0) {
                    break;
                }
                i++;
            }
            k++;
        }
        return k;
    }
}