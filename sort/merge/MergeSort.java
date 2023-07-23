/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 19:46:14
 * @LastEditTime: 2022-12-13 20:25:04
 */
package sort.merge;

public class MergeSort {
    int[] tmp;
    public int[] sort(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length-1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);

            merge(nums, left, mid, right);
        }
    }

    private void merge(int[] nums, int left, int mid, int right) {
        // 在[left, right]区间内复制nums到tmp
        // if (right + 1 - left >= 0) System.arraycopy(nums, left, tmp, left, right + 1 - left);
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }

        int i = left, j = mid + 1;
        for (int p = left; p <= right; p++) {
            if (i == mid + 1) {
                nums[p] = tmp[j++];
            } else if (j == right + 1) {
                nums[p] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                nums[p] = tmp[i++];
            } else {
                nums[p] = tmp[j++];
            }
        }
    }
}
