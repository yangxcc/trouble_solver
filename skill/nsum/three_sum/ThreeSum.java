/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-09 16:41:47
 * @LastEditTime: 2023-02-11 21:40:03
 */
package skill.nsum.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 15 middle 三数之和
 * 
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
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
            if (k + 1 < nums.length && nums[k] == nums[k + 1]) {  // 这里得是if
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
