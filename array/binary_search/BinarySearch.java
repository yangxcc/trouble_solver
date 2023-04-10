/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-09-25 13:39:39
 * @LastEditTime: 2023-01-06 14:21:52
 */
package array.binary_search;

public class BinarySearch{
    /**
     * leetcode 704 simple 二分查找
     * @param nums 升序数组
     * @param target 目标数
     * @return 目标数所在索引，不存在返回-1
     */
    public int BasicBinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 相当于[left, right]是一个闭区间，所以下面是left<=right时跳出循环，即left=right+1时跳出循环

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // nums=[2,4,6,8,10], target = 9
        // return left; // 该位置是9应该插入的位置, 剑指offerII 68 simple 查找插入位置
        return -1;
    }

    /**
     * leetcode 34 middle 在排序数组中查找元素的第一个和最后一个位置
     * @param nums 有重复元素的升序排列的数组
     * @param target 目标数
     * @return  返回第一个等于目标数的左边界
     */
    public int findLeftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 假设nums=[1,2,2,2,2,2,2], [-1,0,1,2,2,2,2],
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // 如果target比数组中的所有元素都大，最后的left == right+1 = len(nums)
        // 或者是最后跳出来的也不等于target
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }

        return left;
    }

    /**
     * leetcode 34 middle 在排序数组中查找元素的第一个和最后一个位置
     * @param nums 有重复元素的升序排列的数组
     * @param target 目标数
     * @return  返回第一个等于目标数的右边界
     */
    public int findRightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // 如果target比所有的元素都小，[1,2,2,2,2,2,2]
        // 或者是最后跳出来的也不等于target
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }
}