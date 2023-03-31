package array.binary_search.application;

/**
 * leetcode 153 middle 寻找旋转排序数组中的最小值
 * 
 * 在一个先升序后降序的数组中，以O(nlongn)的时间复杂度找到最小值
 * nums 中的所有整数 互不相同，而且nums原来是一个升序数组
 */
public class Leetcode153 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 这里是left == right的时候就跳出
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                // 最小值肯定是在左边，或者就是这个mid
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
