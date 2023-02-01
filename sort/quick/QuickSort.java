/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 14:18:25
 * @LastEditTime: 2023-02-01 17:17:46
 */
package sort.quick;

import java.util.Random;

import sort.utils;

public class QuickSort {
    public int[] sort(int[] nums) {
        // 随机选出一个数和第一个数交换一下，避免极端情况
        // utils.swap(nums, 0, (int)(Math.random()*nums.length));
        shuffle(nums);
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

    // shuffle函数用来将数组随机打乱
    public void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // rand.nextInt(n)生成的是[0, n)的整数
            int r = rand.nextInt(n-i) + i;
            utils.swap(nums, i, r);
        }
    }
}
