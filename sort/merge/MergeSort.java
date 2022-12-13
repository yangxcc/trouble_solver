/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 19:46:14
 * @LastEditTime: 2022-12-13 20:25:04
 */
package sort.merge;

public class MergeSort {
    public int[] sort(int[] nums) {
        int[] tmp = new int[nums.length];
        return sortHeler(nums, tmp, 0, nums.length-1);
    }

    public int[] sortHeler(int[] nums, int[] tmp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sortHeler(nums, tmp, left, mid);
            sortHeler(nums, tmp, mid+1, right);

            return merge(nums, tmp, left, mid, right);
        } 
        return new int[0];
    }

    public int[] merge(int[] nums, int[] tmp, int left, int mid, int right) {
        int idx = left, i = left, j = mid+1;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                tmp[idx++] = nums[i++];
            } else {
                tmp[idx++] = nums[j++];
            }
        }

        while (i <= mid) {
            tmp[idx++] = nums[i++];
        }
        while (j <= right) {
            tmp[idx++] = nums[j++];
        }

        for (int k = left; k <= right; k++) {
            nums[k++] = tmp[idx++];
        }

        return nums;
    }
}
