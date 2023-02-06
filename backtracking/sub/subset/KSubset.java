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
        // buckets[i]表示第i个子集的和
        int[] buckets = new int[k];

        // 通过排序，加入回溯过程，没有这个过程在leetcode中超时
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        return backtrack(nums, 0, target, buckets);
    }

    // 与其他题目不同的是，本题不是以nums为基础，而是以buckets数组为基础，回溯函数作为指针，在buckets数组上游走
    // 其实，说白了，就是nums[idx]这个数要放到哪个bucket中
    public boolean backtrack(int[] nums, int idx, int target, int[] buckets) {
        if (idx == nums.length) {
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != target) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + nums[idx] > target) {
                continue;
            }

            buckets[i] += nums[idx];

            if (backtrack(nums, idx + 1, target, buckets)) {
                return true;
            }

            buckets[i] -= nums[idx];
        }

        return false;
    }
}
