/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-06 13:49:23
 * @LastEditTime: 2023-03-04 17:19:58
 */
package backtracking.sub.subset;

import java.util.Arrays;
import java.util.Comparator;;

/**
 * leetcode 698 middle 划分为k个相等的子集
 * 
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 */
public class KSubset {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;
        // 可以想象成有k个桶
        // 用来记录数组中的元素是否被访问过了
        boolean[] visited = new boolean[n];

        return backtrack(nums, k, target, 0, visited);
    }


    public boolean backtrack(int[] nums, int bucketIdx, int target, int bucketCapcity, boolean[] visited) {
        if (bucketIdx == 0) {
            // 剩下桶的个数为0
            return true;
        }

        if (bucketCapcity == target) {
            return backtrack(nums, bucketIdx - 1, target, 0, visited);
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[nums[i]]) {
                continue;
            }
            // 题目中说了所有元素都大于0
            if (bucketCapcity + nums[i] > target) {
                return false;
            }

            bucketCapcity += nums[i];
            visited[i] = true;

            if (backtrack(nums, bucketIdx, target, bucketCapcity, visited)) {
                return true;
            }

            bucketCapcity -= nums[i];
            visited[i] = false;
        }

        return false;
    }
}
