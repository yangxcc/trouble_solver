/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-07-04 22:40:35
 * @LastEditTime: 2023-07-06 22:03:56
 */
package array.binary_search.application;

/**
 * leetcode 852 simple 在先升序后降序的数组中找到最大值（不含重复数字）
 *
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 *
 * 你必须设计并实现时间复杂度为 O(log(n)) 的解决方案。
 */
public class Leetcode852 {
    public int peakIndexInMountainArray(int[] arr) {
        if (arr.length < 2) {
            return arr[0];
        }

        // 因为这道题目中没有说明数组是由一个升序数组旋转得到的，所以不能够通过arr[mid]和arr[left]来进行比较
        int left = 0, right = arr.length - 1;
        while (left < right) { // 也要注意这里不是<=
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                // 在右边或者是已经是最大的了
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }




    // arr是一个有重复元素的不严格升序数组，比如[1,2,2,2,2,3,1]
    public int processRepeatArray(int[] arr) {
        if (arr.length < 2) {
            return arr[0];
        }

        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                // 在右边或者已经是最大了
                right = mid;
            } else if (arr[mid] < arr[mid + 1]) {
                // 在左边
                left = mid + 1;
            } else {
                // 碰到相等的了，这个相等的可能是最大，也可能不是
                if (arr[left] < arr[right]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return left;
    }

    /**
     * leetcode 162 寻找峰值 middle
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 题目要求时间复杂度在O(logn)
     */
    public static int findPeekElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid > 0 && mid + 1 < nums.length && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (mid > 0 && nums[mid] < nums[mid - 1]) {
                // 往左走，峰值在左边
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 疑问，这里为什么是right？
        return right;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,3,5,6,4};
        findPeekElement(arr);
    }
}
