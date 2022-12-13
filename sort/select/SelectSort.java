/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 17:52:51
 * @LastEditTime: 2022-12-13 18:08:43
 */
package sort.select;

import sort.utils;

public class SelectSort {
    public int[] sort(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int min = -1;
        for (int i = 0; i < nums.length; i++) {
            min = i;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            if (i != min) {
                utils.swap(nums, i, min);
            }
        }
        return nums;
    }
}
