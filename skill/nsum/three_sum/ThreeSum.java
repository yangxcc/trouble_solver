/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-09 16:41:47
 * @LastEditTime: 2023-07-09 20:38:32
 */
package skill.nsum.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * leetcode 15 middle 三数之和
 * 
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j !=
 * k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 2) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int k = nums.length - 1; k >= 2; k--) {
            if (k + 1 < nums.length && nums[k] == nums[k + 1]) { // 这里得是if
                continue;
            }
            for (int i = 0, j = k - 1; i < j;) {
                if (nums[i] + nums[j] == -nums[k]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    res.add(temp);
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    i++;
                    j--;
                } else if (nums[i] + nums[j] < -nums[k]) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }
}

/**
 * leetcode 16 middle 最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 */
class Leetcode16 {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;  // target和三数之和的差值
        for (int i = 0; i < nums.length - 2; i++) {
            int sum = nums[i] + twoSumClosest(nums, target - nums[i], i + 1);
            if (Math.abs(target - sum) < Math.abs(diff)) {
                diff = target - sum;
            }
        }

        return target - diff;
    }

    private int twoSumClosest(int[] nums, int target, int k) {
        int left = k, right = nums.length - 1;
        int diff = Integer.MAX_VALUE;  // target和两数之和的差值
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (Math.abs(target - sum) < Math.abs(diff)) {
                diff = target - sum;
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return target - diff;
    }
}

/**
 * leetcode 259 较小的三数之和 middle
 * 给定⼀个⻓度为 n 的整数数组和⼀个⽬标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成⽴的三元组 i, j, k 个数（0 <= i < j < k < n）。
 */
class Leetcode259 {
    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            ans += twoSumSmaller(nums, target - nums[i], i + 1);
        }
        return ans;
    }

    private int twoSumSmaller(int[] nums, int target, int start) {
        int left = start, right = nums.length - 1;
        int count = 0;
        while (left < right) {
            if (nums[left] + nums[right] >= target) {
                right--;
            } else {
                // 从[left+1, right]都能够满足两数之和小于target
                count += right - left;
                left++;
            }
        }
        return count;
    }
}