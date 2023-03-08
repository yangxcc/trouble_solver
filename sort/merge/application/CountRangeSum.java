/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-20 14:12:39
 * @LastEditTime: 2023-02-23 18:42:42
 */
package sort.merge.application;

/**
 * leetcode 327 hard 区间和的个数
 * 
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 */
// 不知道哪里出问题了，为啥Java中用归并排序还是会超时...👿
public class CountRangeSum {
    int count = 0;
    long[] tmp;
    int lower, upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        // 构建前缀和数组，注意 int 可能溢出，⽤ long 存储
        long[] preSum = new long[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }

        tmp = new long[nums.length+1];
        mergeSort(preSum, 0, preSum.length-1);
        return count;
    }

    public void mergeSort(long[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid+1, right);
            merge(nums, left, mid, right);
        }
    }

    public void merge(long[] nums, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }

        for (int i = left; i <= mid; i++) {
            int start = mid+1, end = mid+1;
            while (start <= right && tmp[start] - tmp[i] < lower) {
                start++;
            }

            while (end <= right && tmp[end] - tmp[i] <= upper) {
                end++;
            } 
            count += end - start;
        }

        int i = left, j = mid+1;
        for (int p = left; p <= right; p++) {
            if (i == mid+1) {
                nums[p] = tmp[j++];
            } else if (j == right+1) {
                nums[p] = tmp[i++];
            } else if (tmp[i] < tmp[j]) {
                nums[p] = tmp[i++];
            } else {
                nums[p] = tmp[j++];
            }
        }
    }
}
