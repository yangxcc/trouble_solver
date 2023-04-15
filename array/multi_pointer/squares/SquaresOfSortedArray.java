/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-01 11:40:02
 * @LastEditTime: 2023-04-15 17:21:23
 */
package array.multi_pointer.squares;

/**
 * leetcode 977 simple 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 */
public class SquaresOfSortedArray {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int left = 0, right = nums.length - 1;
        int[] ans = new int[n];
        int i = n - 1;

        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                ans[i] = nums[left] * nums[left];
                left++;
            } else {
                ans[i] = nums[right] * nums[right];
                right--;
            }
            i--;
        }

        return ans;
    }
}

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 */
class day0415 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int left = 0, right = n - 1;
        int idx = n - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                ans[idx--] = nums[left] * nums[left];
                left++;
            } else {
                ans[idx--] = nums[right] * nums[right];
                right--;
            }
        }

        return ans;
    }
}