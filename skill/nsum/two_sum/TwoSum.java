/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-09 16:26:07
 * @LastEditTime: 2023-02-11 20:31:55
 */
package skill.nsum.two_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode 1 simple 两数之和
 * 
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> helper = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (helper.containsKey(nums[i])) {
                return new int[] { i, helper.get(nums[i]) };
            }
            helper.put(target - nums[i], i);
        }

        // for (int i = 0; i < nums.length; i++) {
        // if (helper.containsKey(nums[i])) {
        // ans[0] = i;
        // ans[1] = helper.get(nums[i]);
        // }
        // }

        return ans;
    }

    /**
     * 如果不是要求返回索引，而是要求返回元素，那就简单多了，直接排序+双指针
     * 
     * @param nums
     * @param target
     * @return
     */
    public int[] TwoSumReturnEle(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (nums[i] + nums[j] == target) {
                return new int[] { nums[i], nums[j] };
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[] {};
    }

    /**
     * 如果nums中有多个组合能够构成target，且存在重复元素应该怎么办呢
     * 首先，大思路上还是排序+双指针，第二，我们应该去重，最后返回的结果中不能存在重复的，比如[-1, 1], [1, -1]这种的
     * 
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> TwoSumReturnEle2(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0, j = n - 1; i < j; i++, j--) {
            while (i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }

            while (j > 0 && nums[j] == nums[j - 1]) {
                j--;
            }

            if (nums[i] + nums[j] == target) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(nums[i]);
                tmp.add(nums[j]);
                ans.add(new ArrayList<>(tmp));
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return ans;
    }
}