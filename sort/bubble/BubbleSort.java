/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 15:24:12
 * @LastEditTime: 2022-12-13 17:42:13
 */
package sort.bubble;

import sort.utils;

public class BubbleSort {
    public int[] bubbleSort(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    utils.swap(nums, j, j+1);
                }
            }
        }
        return nums;
    }
}
