/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-06 20:10:54
 * @LastEditTime: 2023-05-07 22:16:22
 */
package bag_problem.subset_package;

/**
 * leetcode 416 middle 分割等和子集
 * 
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 组合（回溯）中都能够分成k份，这里分成两份，比较特殊，所以能使用dp
 */
public class PartitionSubset{
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;  // 和是奇数的话，一定是不能分成两份完全相等的
        }
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        // bad case：[1, 2, 5]，不能重复选择，注意和零钱兑换区别，这里的一维数组实际上是状态压缩了
        // for (int num : nums) {
        //     for (int i = 1; i <= sum; i++) {
        //         if (i < num) {
        //             continue;
        //         }

        //         dp[i] = dp[i - num] || dp[i];
        //     }
        // }

        for (int num : nums) {
            for (int i = sum; i >= 1; i--) {
                if (i < num) {
                    continue;
                }

                dp[i] = dp[i - num] || dp[i];
            }
        }
        return dp[sum];
    }
}

class day0507 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum %  2 != 0) {
            return false;
        }

        int n = nums.length;
        // dp[i][j]表示的是前i个数能否组合成sum
        sum /= 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    // 只能是不选择这个数
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }
}