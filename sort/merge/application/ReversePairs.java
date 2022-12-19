/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-19 14:47:47
 * @LastEditTime: 2022-12-19 15:50:08
 */
package sort.merge.application;

public class ReversePairs{
    // 暴力方法O(n^2)会超时
    // 可以使用归并排序的思路
    int count = 0;
    int[] temp;

    public int reversePairs(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        temp = new int[nums.length];
        mergeSort(nums, left, right);
        return count;
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        
        for (int idx = left; idx <= right; idx++) {
            temp[idx] = nums[idx];
        }

        for (int p = left; p <= right; p++) {
            if (i == mid+1) {
                nums[p] = temp[j++];
            } else if (j == right+1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                // [i, mid]这个区间内，i后面的所有元素，直到mid都是比nums[i]大的，共有mid-i+1个
                count += mid-i+1;
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}