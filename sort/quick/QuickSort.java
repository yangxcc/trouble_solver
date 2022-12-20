/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 14:18:25
 * @LastEditTime: 2022-12-20 17:52:10
 */
package sort.quick;

import sort.utils;

public class QuickSort {
    public int[] sort(int[] nums) {
        // 随机选出一个数和第一个数交换一下，避免极端情况
        utils.swap(nums, 0, (int)(Math.random()*(nums.length >> 1)));
        sort(nums, 0, nums.length-1);
        return nums;
    }

    public void sort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(nums, left, right);
        sort(nums, left, pivot-1);
        sort(nums, pivot+1, right);
    }

    public int partition(int[] nums, int left, int right) {
        // 或者是每次partition的时候都随机swap一下
        // swap(nums, left, left + (int)(Math.random()*((right-left) >> 1)));
        int pivot = nums[left];
        int leftIdx = left+1, rightIdx = right;

        while (leftIdx <= rightIdx) {
            while (leftIdx < right && nums[leftIdx] < pivot) {
                leftIdx++;
            }

            while (rightIdx > left && nums[rightIdx] >= pivot) {
                rightIdx--;
            }

            if (leftIdx >= rightIdx) {
                break;
            }

            utils.swap(nums, leftIdx, rightIdx);
        }

        utils.swap(nums, left, rightIdx);
        
        return rightIdx;
    }
}
