/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-09-30 21:32:23
 * @LastEditTime: 2022-12-31 20:33:28
 */
package array.multi_pointer.remove_element;

/**
 * leetcode 27 simple https://leetcode.cn/problems/remove-element/
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 */
public class RemoveEle {
    public int removeElement(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] != val) {
                left++;
            } else {
                if (nums[right] == val) {
                    right--;
                } else {
                    // swap(left, right)
                    int tmp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tmp;
                }
            }
        }
        return left;
    }

    /**
     * leetcode 26 simple. 删除有序数组中的重复项
     * [0,0,1,1,1,2,2,3,3,4]
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int fast = 0; // 当前索引
        int slow = 0; // 无重复项区间的右边界
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
}