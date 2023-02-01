/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-21 14:26:14
 * @LastEditTime: 2023-02-01 17:13:20
 */
package sort.quick.application;

/**
 * leetcode 215 middle 数组中的第k个最大元素
 * 
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class topK {
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length) {
            return -10001;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int position = partition(nums, left, right);
            if (position == nums.length - k) {
                return nums[position];
            } else if (position < nums.length - k) {
                left = position + 1;
            } else {
                right = position - 1;
            }
        }
        return -10001;
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        // 如下这么定义leftIdx和rightIdx，相当于是一个开区间，即
        // [left, leftIdx) <= povot, (rightIdx, right] > pivot
        int leftIdx = left + 1, rightIdx = right;
        // 因此这里是 <= ，这里吃过亏
        while (leftIdx <= rightIdx) {
            while (leftIdx < right && nums[leftIdx] <= pivot) {
                leftIdx++;
            }
            while (rightIdx > left && nums[rightIdx] > pivot) {
                rightIdx--;
            }

            if (leftIdx >= rightIdx) {
                break;
            }

            swap(nums, leftIdx, rightIdx);
        }

        swap(nums, left, rightIdx);
        return rightIdx;
    }

    public void swap(int[] nums, int L, int R) {
        int tmp = nums[L];
        nums[L] = nums[R];
        nums[R] = tmp;
    }
}
